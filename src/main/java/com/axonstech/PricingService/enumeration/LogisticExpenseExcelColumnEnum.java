package com.axonstech.PricingService.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogisticExpenseExcelColumnEnum {
    ZONE_AREA("zoneArea",0,String.class),
    ZONE_CLASS_PRICE("zoneClassPrice",1,String.class),
    EFFECTIVE_DATE("effectiveDate",2, String.class),
    PRODUCT_GROUP("productGroup",3,String.class),
    PRODUCT_SUB_GROUP1("productSubGroup1",4,String.class),
    PRODUCT_SUB_GROUP2("productSubGroup2",5,String.class),
    PRODUCT_GROUP_NAME("productGroupName",6,String.class),
    PRODUCT_SUB_GROUP1_NAME("subGroupName1",7,String.class),
    PRODUCT_SUB_GROUP2_NAME("subGroupName2",8,String.class),
    LOGISTIC_EXP("logisticExp",9, Double.class),
    STATUS("status",10,String.class);

    private String id;
    private Integer index;
    private Class classType;
}
