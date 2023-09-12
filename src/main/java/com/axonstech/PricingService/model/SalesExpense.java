package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "SALES_EXPENSE")
public class SalesExpense {

    @DynamoDBAttribute(attributeName = "CREATE_DATETIME")
    @NonNull
    private Date createDateTime;

    @DynamoDBAttribute(attributeName = "CREATE_BY")
    @NonNull
    private String createBy;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_DATETIME")
    private Date lastUpdateDateTime;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_BY")
    private String lastUpdateBy;

    @DynamoDBHashKey(attributeName = "ID")
    private String id; //companyCode#zoneArea#zoneClassPrice#productGroup

    @DynamoDBAttribute(attributeName = "COMPANY_CODE")
    @NonNull
    private String companyCode;

    @DynamoDBAttribute(attributeName = "ZONE_AREA")
    @NonNull
    private String zoneArea;

    @DynamoDBAttribute(attributeName = "ZONE_CLASS_PRICE")
    @NonNull
    private String zoneClassPrice;

    @DynamoDBRangeKey(attributeName = "EFFECTIVE_DATE")
    @NonNull
    private String effectiveDate;

    @DynamoDBAttribute(attributeName = "PRODUCT_GROUP")
    @NonNull
    private String productGroup;
    
    @DynamoDBAttribute(attributeName = "PRODUCT_GROUP_NAME")
    @NonNull
    private String productGroupName;

    @DynamoDBAttribute(attributeName = "PERCENT_SELLING_EXP")
    @NonNull
    private Double percentSellingExp;

    @DynamoDBAttribute(attributeName = "PERCENT_GA_EXP")
    @NonNull
    private Double percentGaExp;

    @DynamoDBAttribute(attributeName = "STATUS")
    @NonNull
    private String status;

    @DynamoDBAttribute(attributeName = "EXPIRE_DATE")
    private Date expireDate;

}
