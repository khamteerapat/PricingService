package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "LOGISTIC_EXPENSE")
public class LogisticExpense {
    @DynamoDBAttribute(attributeName = "CREATE_DATETIME")
    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    private LocalDateTime createDatetime;

    @DynamoDBAttribute(attributeName = "CREATE_BY")
    private String createBy;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_DATETIME")
    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    private LocalDateTime lastUpdateDatetime;

    @DynamoDBAttribute(attributeName = "LAST_UPDATE_BY")
    private String lastUpdateBy;

    //COMPANY_CODE#ZONE_AREA#ZONE_CLASS_PRICE#PRODUCT_GROUP#PRODUCT_SUB_GROUP1#PRODUCT_SUB_GROUP2
    @DynamoDBHashKey(attributeName = "ID")
    private String id;

    @DynamoDBAttribute(attributeName = "COMPANY_CODE")
    private String companyCode;

    @DynamoDBAttribute(attributeName = "ZONE_AREA")
    private String zoneArea;

    @DynamoDBAttribute(attributeName = "ZONE_CLASS_PRICE")
    private String zoneClassPrice;

    @DynamoDBRangeKey(attributeName = "EFFECTIVE_DATE")
    @DynamoDBTypeConverted( converter = LocalDateConverter.class )
    private LocalDate effectiveDate;

    @DynamoDBAttribute(attributeName = "PRODUCT_GROUP")
    private String productGroup;

    @DynamoDBAttribute(attributeName = "PRODUCT_GROUP_NAME")
    private String productGroupName;

    @DynamoDBAttribute(attributeName = "PRODUCT_SUB_GROUP1")
    private String productSubGroup1;

    @DynamoDBAttribute(attributeName = "PRODUCT_SUB_GROUP1_NAME")
    private String productSubGroup1Name;

    @DynamoDBAttribute(attributeName = "PRODUCT_SUB_GROUP2")
    private String productSubGroup2;

    @DynamoDBAttribute(attributeName = "PRODUCT_SUB_GROUP2_NAME")
    private String productSubGroup2Name;

    @DynamoDBAttribute(attributeName = "LOGISTIC_EXP")
    private Double logisticExp;

    @DynamoDBAttribute(attributeName = "EXPIRE_DATE")
    @DynamoDBTypeConverted( converter = LocalDateConverter.class )
    private LocalDate expireDate;

    @DynamoDBAttribute(attributeName = "STATUS")
    private String status;

    public static class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime>{
        @Override
        public String convert(final LocalDateTime time) {
            return time.toString();
        }

        @Override
        public LocalDateTime unconvert(final String stringValue) {
            return LocalDateTime.parse(stringValue);
        }
    }

    public static class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate>{
        @Override
        public String convert(final LocalDate date) {
            return date.toString();
        }

        @Override
        public LocalDate unconvert(final String stringValue) {
            return LocalDate.parse(stringValue);
        }
    }
}
