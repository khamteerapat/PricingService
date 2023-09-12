package com.axonstech.PricingService.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public enum SalesExpenseExcelColumnEnum {

    ZONE_AREA("zoneArea", 0, String.class),
    ZONE_CLASS_PRICE("zoneClassPrice", 1, String.class),
    EFFECTIVE_DATE("effectiveDate", 2, String.class),
    PRODUCT_GROUP("productGroup", 3, String.class),
    PRODUCT_GROUP_NAME("productGroupName", 4, String.class),
    PERCENT_SELLING_EXP("percentSellingExp", 5, Double.class),
    PERCENT_GA_EXP("percentGaExp", 6, Double.class),
    STATUS("status", 7, String.class);


    private String id;
    private Integer index;
    private Class<?> classType;
}
