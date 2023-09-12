package com.axonstech.PricingService.api;

import com.axonstech.PricingService.exceptions.LogisticExpenseByProductExcelInvalidException;
import com.axonstech.PricingService.service.LogisticExpenseByProductExcelService;
import com.axonstech.PricingService.utils.DownloadUtils;
import com.axonstech.PricingService.utils.ExceptionUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/logisticExpenseByProduct")
public class LogisticExpenseByProductApi {

    @Autowired
    private LogisticExpenseByProductExcelService logisticExpenseByProductExcelService;

    @PostMapping(value = "/importExcel")
    public InputStreamResource postImportExcel(
            HttpServletResponse httpServletResponse,
            @RequestParam("importExcelFile") MultipartFile multipartFile
    ) throws Exception {
        String resultExcel = null;

        try {
            logisticExpenseByProductExcelService.extract(multipartFile.getInputStream(),"CPFGS");
            resultExcel = "Import success !!";
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        } catch (LogisticExpenseByProductExcelInvalidException logisticExpenseByProductExcelInvalidException) {
            ExceptionUtils.log(logisticExpenseByProductExcelInvalidException);
            resultExcel = "Invalid Excel file !!";
        }

        return
                DownloadUtils.download(
                        httpServletResponse,
                        new ByteArrayInputStream(resultExcel.getBytes(StandardCharsets.UTF_8)),
                        "text/plain",
                        String.format("ImportResult.txt")
                );
    }
}
