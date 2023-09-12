package com.axonstech.PricingService.api;

import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.payload.LogisticExpensePayload;
import com.axonstech.PricingService.payload.api.ApiResponsePayload;
import com.axonstech.PricingService.service.LogisticExpenseService;
import com.axonstech.PricingService.utils.ExcelUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logistic-expense")
public class LogisticExpenseApi {
    private static final String RETURN_MESSAGE = "Success";
    private static final String HEADER_COMPANY_CODE = "Company-Code";
    private static final String HEADER_USER = "User";


    private final LogisticExpenseService logisticExpenseService;
    public LogisticExpenseApi(LogisticExpenseService logisticExpenseService) {
        this.logisticExpenseService = logisticExpenseService;
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponsePayload<List<LogisticExpense>>> getAllLogisticExpense(){
        List<LogisticExpense> responseList = logisticExpenseService.getAllLogisticExpense();
        return ResponseEntity.ok( new ApiResponsePayload<>(RETURN_MESSAGE,responseList,null));
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponsePayload<LogisticExpense>> getByKeyLogisticExpense(@RequestParam String key, @RequestParam String sortKey){
        LogisticExpense logisticExpense = logisticExpenseService.getByKeyLogisticExpense(key,sortKey);
        return ResponseEntity.ok(new ApiResponsePayload<>(RETURN_MESSAGE,logisticExpense,null));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponsePayload<String>>  createLogisticExpense(HttpServletRequest request, @Valid @RequestBody LogisticExpensePayload logisticExpensePayload){
        String companyCode = request.getHeader(HEADER_COMPANY_CODE);
        String user = request.getHeader(HEADER_USER);
        if(user == null || companyCode == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            logisticExpenseService.createLogisticExpense(user, companyCode, logisticExpensePayload);
            return ResponseEntity.ok(new ApiResponsePayload<>(RETURN_MESSAGE,null,null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponsePayload<String>> updateLogisticExpense(HttpServletRequest request,@Valid @RequestBody LogisticExpensePayload logisticExpensePayload){
        String companyCode = request.getHeader(HEADER_COMPANY_CODE);
        String user = request.getHeader(HEADER_USER);
        if(user == null || companyCode == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            logisticExpenseService.updateLogisticExpense(user, companyCode, logisticExpensePayload);
            return ResponseEntity.ok(new ApiResponsePayload<>(RETURN_MESSAGE,null,null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponsePayload<String>> deleteLogisticExpense(@RequestParam String key, @RequestParam LocalDate sortKey){
        logisticExpenseService.deleteLogisticExpense(key,sortKey);
        return ResponseEntity.ok(new ApiResponsePayload<>(RETURN_MESSAGE,null,null));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponsePayload<String>> uploadLogisticExpense(HttpServletRequest request, @RequestBody MultipartFile file){
        String companyCode = request.getHeader(HEADER_COMPANY_CODE);
        String user = request.getHeader(HEADER_USER);
        if(user == null || companyCode == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponsePayload<>("Bad Request.",null,null));
        }
        else {
            if (ExcelUtil.hasExcelFormat(file)) {
                logisticExpenseService.uploadLogisticExpense(user,companyCode, file);
                return ResponseEntity.ok(new ApiResponsePayload<>(RETURN_MESSAGE,null,null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponsePayload<>("Please upload an excel file!",null,null));
            }
        }
    }
}
