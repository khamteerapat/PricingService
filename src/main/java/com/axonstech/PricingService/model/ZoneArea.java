package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "ZoneArea")
public class ZoneArea extends StandardDataModel {
    @DynamoDBHashKey
    private String zoneArea;

    @DynamoDBAttribute
    private String zoneAreaName;
}
