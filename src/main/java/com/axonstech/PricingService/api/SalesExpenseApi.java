package com.axonstech.PricingService.api;


import com.axonstech.PricingService.exceptions.SalesExpenseExcelInvalidException;
import com.axonstech.PricingService.model.SalesExpense;
import com.axonstech.PricingService.payload.SalesExpensePayload;
import com.axonstech.PricingService.service.SalesExpenseDataService;
import com.axonstech.PricingService.service.SalesExpenseExcelService;
import com.axonstech.PricingService.utils.DownloadUtils;
import com.axonstech.PricingService.utils.ExceptionUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/salesExpense")
public class SalesExpenseApi {

    @Autowired
    private SalesExpenseDataService dataService;

    @Autowired
    private SalesExpenseExcelService salesExpenseExcelService;

    public SalesExpenseApi(SalesExpenseDataService dataService, SalesExpenseExcelService salesExpenseExcelService) {
        this.dataService = dataService;
        this.salesExpenseExcelService = salesExpenseExcelService;
    }

    private static final String RESULT = "Success";

    @PostMapping("/save")
    public String saveSalesExpense(@RequestBody SalesExpensePayload payload) {
        dataService.saveSalesExpense(payload);
        return RESULT;
    }

    @GetMapping("/getAll")
    public List<SalesExpense> getAllSalesExpense() {
        return dataService.getAllSalesExpense();
    }

    @GetMapping("/getById")
    public SalesExpense getByIdSalesExpense(@RequestParam String id, @RequestParam String sortKey) {
        return dataService.getByIdSalesExpense(id, sortKey);
    }

    @DeleteMapping("/delete")
    public String deleteSalesExpense(@RequestParam String id, @RequestParam String sortKey) {
        dataService.deleteSalesExpense(id,sortKey);
        return RESULT;
    }

    @PutMapping("/update")
    public String updateSalesExpense(@RequestParam String id, @RequestParam String sortKey, @RequestBody SalesExpensePayload payload) {
        dataService.updateSalesExpense(id, sortKey, payload);
        return RESULT;
    }

    @PostMapping("/importExcel")
    public InputStreamResource postImportExcel(HttpServletResponse httpServletResponse, @RequestParam("importExcelFile") MultipartFile multipartFile) throws IOException {
        String resultExcel = null;

        try {
            salesExpenseExcelService.extract(multipartFile.getInputStream(),"CPFGS");
            resultExcel = "Import success !!";
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (SalesExpenseExcelInvalidException salesExpenseExcelInvalidException) {
            ExceptionUtils.log(salesExpenseExcelInvalidException);
            resultExcel = "Invalid Excel file !!";
        }

        return
                DownloadUtils.download(
                        httpServletResponse,
                        new ByteArrayInputStream(resultExcel.getBytes(StandardCharsets.UTF_8)),
                        "text/plain",
                        "ImportResult.txt"
                );
    }

}
