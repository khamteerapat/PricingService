package com.axonstech.PricingService.utils;

import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {
    private ExcelUtil(){}
    public static boolean hasExcelFormat(MultipartFile file) {
        String type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        return type.equals(file.getContentType());
    }
}
