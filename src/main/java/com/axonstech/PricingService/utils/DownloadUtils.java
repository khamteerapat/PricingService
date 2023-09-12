package com.axonstech.PricingService.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.InputStream;

public class DownloadUtils {


    private DownloadUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static InputStreamResource download(
            HttpServletResponse httpServletResponse, InputStream inputStream, @NonNull String contentType, String fileName
    ) throws IOException {
        if (StringUtils.hasText(contentType)) {
            httpServletResponse.setContentType(contentType);
        }
        httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        return new InputStreamResource(inputStream);

    }

    public static ByteArrayResource download(
            HttpServletResponse httpServletResponse, byte[] byteArray, @NonNull String contentType, String fileName
    ) throws IOException {
        if (StringUtils.hasText(contentType)) {
            httpServletResponse.setContentType(contentType);
        }
        httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        return new ByteArrayResource(byteArray);

    }

}
