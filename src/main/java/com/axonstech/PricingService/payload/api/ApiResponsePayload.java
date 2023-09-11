package com.axonstech.PricingService.payload.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponsePayload<T> {
    private String message;
    private T data;
    private ApiPaginationPayload pagination;
}
