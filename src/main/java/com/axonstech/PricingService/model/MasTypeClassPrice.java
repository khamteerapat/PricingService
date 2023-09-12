package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "MAS_TYPE_CLASS_PRICE")
public class MasTypeClassPrice {

    @DynamoDBAttribute(attributeName = "CREATE_DATETIME")
    @NonNull
    private Date createDatetime;
    
    @DynamoDBAttribute(attributeName = "CREATE_BY")
    @NonNull
    private String createBy;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_DATETIME")
    private Date lastUpdateDatetime;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_BY")
    private String lastUpdateBy;

    @DynamoDBAttribute(attributeName = "ZONE_AREA")
    @NonNull
    private String zoneArea;

    @DynamoDBAttribute(attributeName = "ZONE_CLASS_PRICE")
    @NonNull
    private String zoneClassPrice;

    @DynamoDBHashKey(attributeName = "VENDOR_GROUP")
    @NonNull
    private String vendorGroup;

    @DynamoDBAttribute(attributeName = "TYPE_CLASS_PRICE")
    @NonNull
    private String typeClassPrice;

    @DynamoDBRangeKey(attributeName = "SORT_ID")
    @NonNull
    private String sortId; //zoneArea#zoneClassPrice#typeClassPrice

    @DynamoDBAttribute(attributeName = "ZONE_AREA_NAME")
    @NonNull
    private String zoneAreaName;

    @DynamoDBAttribute(attributeName = "ZONE_CLASS_PRICE_NAME")
    @NonNull
    private String zoneClassPriceName;

    @DynamoDBAttribute(attributeName = "VENDOR_GROUP_NAME")
    @NonNull
    private String vendorGroupName;

    @DynamoDBAttribute(attributeName = "TYPE_CLASS_PRICE_NAME")
    @NonNull
    private String typeClassPriceName;

    @DynamoDBAttribute(attributeName = "STATUS")
    @NonNull
    private String status;


}
