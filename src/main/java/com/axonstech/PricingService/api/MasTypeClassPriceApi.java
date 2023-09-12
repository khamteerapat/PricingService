package com.axonstech.PricingService.api;

import com.axonstech.PricingService.exceptions.MasTypeClassPriceExcelInvalidException;
import com.axonstech.PricingService.model.MasTypeClassPrice;
import com.axonstech.PricingService.payload.MasTypeClassPricePayload;
import com.axonstech.PricingService.payload.api.ApiResponsePayload;
import com.axonstech.PricingService.service.MasTypeClassPriceDataService;
import com.axonstech.PricingService.service.MasTypeClassPriceExcelService;
import com.axonstech.PricingService.utils.DownloadUtils;
import com.axonstech.PricingService.utils.ExceptionUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/masTypeClassPrice")
public class MasTypeClassPriceApi {

    @Autowired
    private MasTypeClassPriceDataService masTypeClassPriceDataService;
    @Autowired
    private MasTypeClassPriceExcelService masTypeClassPriceExcelService;

    public MasTypeClassPriceApi(MasTypeClassPriceDataService masTypeClassPriceDataService, MasTypeClassPriceExcelService masTypeClassPriceExcelService) {
        this.masTypeClassPriceDataService = masTypeClassPriceDataService;
        this.masTypeClassPriceExcelService = masTypeClassPriceExcelService;
    }

    private static final String MESSAGE = "Success";

    @PostMapping("/save")
    public ResponseEntity<ApiResponsePayload<List<MasTypeClassPricePayload>>> saveMasTypeClassPrice(@RequestBody MasTypeClassPricePayload masTypeClassPricePayload) {

        masTypeClassPriceDataService.saveMasTypeClassPrice(masTypeClassPricePayload);
        List<MasTypeClassPricePayload> responseList = new ArrayList<>();
        ApiResponsePayload<List<MasTypeClassPricePayload>> response = new ApiResponsePayload<>(MESSAGE, responseList, null);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/list")
    public List<MasTypeClassPrice> listMasTypeClassPrice() {
        return masTypeClassPriceDataService.getAllMasTypeClassPrice();
    }

    @GetMapping("/get")
    public MasTypeClassPrice getByIdMasTypeClassPrice(@RequestParam String id, @RequestParam String sortKey) {
        return masTypeClassPriceDataService.getByIdMasTypeClassPrice(id, sortKey);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponsePayload<List<MasTypeClassPricePayload>>> deleteMasTypeClassPrice(@RequestParam String id, @RequestParam String sortKey) {

        masTypeClassPriceDataService.deleteMasTypeClassPrice(id, sortKey);
        List<MasTypeClassPricePayload> responseList = new ArrayList<>();
        ApiResponsePayload<List<MasTypeClassPricePayload>> response = new ApiResponsePayload<>(MESSAGE, responseList, null);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponsePayload<List<MasTypeClassPricePayload>>> updateMasTypeClassPrice(@RequestParam String id, @RequestParam String sortKey, @RequestBody MasTypeClassPricePayload masTypeClassPricePayload) {

        masTypeClassPriceDataService.updateMasTypeClassPrice(id, sortKey, masTypeClassPricePayload);
        List<MasTypeClassPricePayload> responseList = new ArrayList<>();
        ApiResponsePayload<List<MasTypeClassPricePayload>> response = new ApiResponsePayload<>(MESSAGE, responseList, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/importExcel")
    public InputStreamResource postImportExcel (HttpServletResponse httpServletResponse, @RequestParam("importExcelFile") MultipartFile multipartFile) throws IOException {
        String resultExcel = null;

        try{
            masTypeClassPriceExcelService.extract(multipartFile.getInputStream());
            resultExcel = "Import success !!";
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (MasTypeClassPriceExcelInvalidException masTypeClassPriceExcelInvalidException) {
            ExceptionUtils.log(masTypeClassPriceExcelInvalidException);
            resultExcel = "Invalid Excel file !!";
        }
        return
                DownloadUtils.download(
                        httpServletResponse, new ByteArrayInputStream(resultExcel.getBytes(StandardCharsets.UTF_8)),
                        "text/plain",
                        "ImportResult.txt"
                );
    }
}
