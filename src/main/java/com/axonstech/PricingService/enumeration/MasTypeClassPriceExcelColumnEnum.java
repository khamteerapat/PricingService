package com.axonstech.PricingService.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MasTypeClassPriceExcelColumnEnum {

    ZONE_AREA("zoneArea", 0, String.class),
    ZONE_CLASS_PRICE("zoneClassPrice", 1, String.class),
    VENDOR_GROUP("vendorGroup", 2, String.class),
    TYPE_CLASS_PRICE("typeClassPrice", 3, String.class),
    ZONE_AREA_NAME("zoneAreaName", 4, String.class),
    ZONE_CLASS_PRICE_NAME("zoneClassPriceName", 5, String.class),
    VENDOR_GROUP_NAME("vendorGroupName", 6, String.class),
    TYPE_CLASS_PRICE_NAME("typeClassPriceName", 7, String.class),
    STATUS("status", 8, String.class);


    private String id;
    private Integer index;
    private Class<?> classType;
}
