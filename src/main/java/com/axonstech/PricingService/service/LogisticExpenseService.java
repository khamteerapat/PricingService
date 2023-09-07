package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.payload.LogisticExpensePayload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticExpenseService {
    private final DynamoDBMapper dynamoDBMapper;

    public LogisticExpenseService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void createLogisticExpense(LogisticExpensePayload logisticExpensePayload){
        dynamoDBMapper.save(buildObject(logisticExpensePayload));
    }

    public List<LogisticExpense> getAllLogisticExpense(){
        DynamoDBScanExpression dbScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(LogisticExpense.class,dbScanExpression);
    }

    public LogisticExpense getByKeyLogisticExpense(String key,String sortKey){
        return dynamoDBMapper.load(LogisticExpense.class,key,sortKey);
    }

    public void updateLogisticExpense(LogisticExpensePayload logisticExpensePayload){
        DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
                .build();
        dynamoDBMapper.save(buildObject(logisticExpensePayload), dynamoDBMapperConfig);
    }

    public void deleteLogisticExpense(String key,String sortKey){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setId(key);
        logisticExpense.setEffectiveDate(sortKey);
        dynamoDBMapper.delete(logisticExpense);
    }

    private LogisticExpense buildObject(LogisticExpensePayload logisticExpensePayload){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setCreateDatetime(logisticExpensePayload.getCreateDatetime());
        logisticExpense.setCreateBy(logisticExpensePayload.getCreateBy());
        logisticExpense.setLastUpdateDatetime(logisticExpensePayload.getLastUpdateDatetime());
        logisticExpense.setLastUpdateBy(logisticExpensePayload.getLastUpdateBy());
        logisticExpense
                .setId(
                        logisticExpensePayload.getCompanyCode() +
                        "#" +
                        logisticExpensePayload.getZoneArea() +
                        "#" +
                        logisticExpensePayload.getZoneClassPrice() +
                        "#" +
                        logisticExpensePayload.getProductGroup() +
                        "#" +
                        logisticExpensePayload.getProductSubGroup1() +
                        "#" +
                        logisticExpensePayload.getProductSubGroup2()
                );
        logisticExpense.setEffectiveDate(logisticExpensePayload.getEffectiveDate());
        logisticExpense.setProductGroupName(logisticExpensePayload.getProductGroupName());
        logisticExpense.setProductSubGroup1Name(logisticExpensePayload.getProductSubGroup1Name());
        logisticExpense.setProductSubGroup2Name(logisticExpensePayload.getProductSubGroup2Name());
        logisticExpense.setLogisticExp(logisticExpensePayload.getLogisticExp());
        logisticExpense.setExpireDate(logisticExpensePayload.getExpireDate());
        logisticExpense.setStatus(logisticExpensePayload.getStatus());
        return logisticExpense;
    }
}
