package com.axonstech.PricingService.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtils {


    private ExceptionUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static void log(Throwable throwable) {
        log.debug("Error", throwable);
    }

}
