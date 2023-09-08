package com.axonstech.PricingService.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogisticExpenseExcelColumnEnum {
    COMPANY_CODE("companyCode",0,String.class),
    ZONE_AREA("zoneArea",1,String.class),
    ZONE_CLASS_PRICE("zoneClassPrice",2,String.class),
    PRODUCT_GROUP("productGroup",3,String.class),
    PRODUCT_SUB_GROUP1("productSubGroup1",4,String.class),
    PRODUCT_SUB_GROUP2("productSubGroup2",5,String.class),
    EFFECTIVE_DATE("effectiveDate",6, String.class),
    PRODUCT_GROUP_NAME("productGroupName",7,String.class),
    PRODUCT_SUB_GROUP1_NAME("productSubGroup1Name",8,String.class),
    PRODUCT_SUB_GROUP2_NAME("productSubGroup2Name",9,String.class),
    LOGISTIC_EXP("logisticExp",10, Double.class),
    STATUS("status",11,String.class);

    private String id;
    private Integer index;
    private Class classType;
}
