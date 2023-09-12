package com.axonstech.PricingService.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MasTypeClassPricePayload {

    private String createBy;
    private Date createDatetime;
    private String lastUpdateBy;
    private Date lastUpdateDatetime;
    private String zoneArea;
    private String zoneClassPrice;
    private String vendorGroup;
    private String typeClassPrice;
    private String sortId;
    private String zoneAreaName;
    private String zoneClassPriceName;
    private String vendorGroupName;
    private String typeClassPriceName;
    private String status;
}
