package com.axonstech.PricingService.exceptions;

public class LogisticExpenseExcelInvalidException extends RuntimeException{
    public LogisticExpenseExcelInvalidException() {
        super();
    }
    public LogisticExpenseExcelInvalidException(String message) {
        super(message);
    }
}
