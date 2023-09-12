package com.axonstech.PricingService.utils;

import org.slf4j.Logger;

public class LogUtils {


    private LogUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static void simpleInfo(Logger logger, String message) {
        logger.info(
                String.format(
                        "[%s] %s", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }

    public static void beginInfo(Logger logger, String message) {
        logger.info(
                String.format(
                        "[%s] %s ...", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }

    public static void endInfo(Logger logger, String message) {
        logger.info(
                String.format(
                        "[%s] Done %s !!!", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }


    public static void simpleDebug(Logger logger, String message) {
        logger.debug(
                String.format(
                        "[%s] %s", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }

    public static void beginDebug(Logger logger, String message) {
        logger.debug(
                String.format(
                        "[%s] %s ...", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }

    public static void endDebug(Logger logger, String message) {
        logger.debug(
                String.format(
                        "[%s] Done %s !!!", DateTimeUtils.nowDateTimeWithSecondText(), message
                )
        );
    }

}
