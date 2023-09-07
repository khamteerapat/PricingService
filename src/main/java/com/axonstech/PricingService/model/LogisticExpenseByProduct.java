package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "LogisticExpenseByProduct")
public class LogisticExpenseByProduct extends StandardDataModel{
    @DynamoDBHashKey
    private String id;//zoneArea#zoneClassPrice#productCode
    @DynamoDBRangeKey
    private Date effectiveDate;
    @DynamoDBAttribute
    private String companyCode;
    @DynamoDBAttribute
    private String zoneArea;
    @DynamoDBAttribute
    private String zoneClassPrice;
    @DynamoDBAttribute
    private String productCode;
    @DynamoDBAttribute
    private String productName;
    @DynamoDBAttribute
    private Double logisticExp;
    @DynamoDBAttribute
    private Date expireDate;
    @DynamoDBAttribute
    private String status;
}
