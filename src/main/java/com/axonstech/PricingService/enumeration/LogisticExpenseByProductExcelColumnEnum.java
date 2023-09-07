package com.axonstech.PricingService.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
public enum LogisticExpenseByProductExcelColumnEnum {
    ZONE_AREA("zoneArea",0,String.class),
    ZONE_CLASS_PRICE("zoneClassPrice",1,String.class),
    EFFECTIVE_DATE("effectiveDate",2, Date.class),
    PRODUCT_CODE("productCode",3,String.class),
    PRODUCT_NAME("productName",4,String.class),
    LOGISTIC_EXP("logisticExp",5, Double.class),
    STATUS("status",6,String.class)
    ;

    private String id;
    private Integer index;
    private Class classType;
}
