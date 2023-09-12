package com.axonstech.PricingService.exceptions;

import com.axonstech.PricingService.payload.api.ApiResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LogisticExpenseExcelInvalidException.class)
    public ResponseEntity<ApiResponsePayload<Object>> handleExcelInvalidException(LogisticExpenseExcelInvalidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponsePayload<>(ex.getMessage(),null,null));
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ApiResponsePayload<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponsePayload<>(ex.getMessage(),null,null));
    }
}
