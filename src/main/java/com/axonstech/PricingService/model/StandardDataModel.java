package com.axonstech.PricingService.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class StandardDataModel {
    @DynamoDBAttribute
    private String createdBy;
    @DynamoDBAttribute
    private Date createDatetime;
    @DynamoDBAttribute
    private String updatedBy;
    @DynamoDBAttribute
    private Date updateDatetime;
}
