package com.axonstech.PricingService.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalesExpensePayload {

    private String id;
    private String companyCode;
    private String zoneArea;
    private String zoneClassPrice;
    private String productGroup;
    private String effectiveDate;
    private String createBy;
    private String lastUpdateBy;
    private String productGroupName;
    private Double percentSellingExp;
    private Double percentGaExp;
    private String status;
    private Date createDateTime;
    private Date lastUpdateDateTime;
    private Date expireDate;
}
