package com.axonstech.PricingService.api;

import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.payload.LogisticExpensePayload;
import com.axonstech.PricingService.service.LogisticExpenseService;
import com.axonstech.PricingService.utils.ExcelHelper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/logistic-expense")
public class LogisticExpenseApi {

    private static final String RETURN_MESSAGE = "Success";

    private final LogisticExpenseService logisticExpenseService;
    public LogisticExpenseApi(LogisticExpenseService logisticExpenseService) {
        this.logisticExpenseService = logisticExpenseService;
    }

    @GetMapping("/list")
    public List<LogisticExpense> getAllLogisticExpense(){
        return logisticExpenseService.getAllLogisticExpense();
    }

    @GetMapping("/get")
    public LogisticExpense getByKeyLogisticExpense(@RequestParam String key, @RequestParam String sortKey){
        return logisticExpenseService.getByKeyLogisticExpense(key,sortKey);
    }

    @PostMapping("/create")
    public String createLogisticExpense(@Valid @RequestBody LogisticExpensePayload logisticExpensePayload){
        logisticExpenseService.createLogisticExpense(logisticExpensePayload);
        return RETURN_MESSAGE;
    }

    @PutMapping("/update")
    public String updateLogisticExpense(@RequestParam String key, @RequestParam String sortKey,@Valid @RequestBody LogisticExpensePayload logisticExpensePayload){
        logisticExpenseService.updateLogisticExpense(key,sortKey,logisticExpensePayload);
        return RETURN_MESSAGE;
    }

    @DeleteMapping("/delete")
    public String deleteLogisticExpense(@RequestParam String key, @RequestParam String sortKey){
        logisticExpenseService.deleteLogisticExpense(key,sortKey);
        return RETURN_MESSAGE;
    }

    @PostMapping("/upload")
    public String uploadLogisticExpense(@RequestBody MultipartFile file){
        if (ExcelHelper.hasExcelFormat(file)) {
            logisticExpenseService.uploadLogisticExpense(file);
            return RETURN_MESSAGE;
        }
        else{
            return "Please upload an excel file!";
        }
    }
}
