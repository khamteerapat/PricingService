package com.axonstech.PricingService.payload.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiPaginationPayload {

    private Integer currentPage;
    private Integer totalPage;
    private Integer currentCount;
    private Integer totalCount;
}
