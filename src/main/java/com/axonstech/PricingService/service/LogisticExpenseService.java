package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.exceptions.LogisticExpenseExcelInvalidException;
import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.payload.LogisticExpensePayload;
import com.axonstech.PricingService.utils.ExcelHelper;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

    public void updateLogisticExpense(String key,String sortKey,LogisticExpensePayload logisticExpensePayload){
        DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .build();
        dynamoDBMapper.save(buildObject(key,sortKey,logisticExpensePayload), dynamoDBMapperConfig);
    }

    public void deleteLogisticExpense(String key,String sortKey){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setId(key);
        logisticExpense.setEffectiveDate(sortKey);
        dynamoDBMapper.delete(logisticExpense);
    }

    public void uploadLogisticExpense(MultipartFile file){
        try {
            List<LogisticExpense> logisticExpenseList = ExcelHelper.excelToLogisticExpense(file.getInputStream());
            dynamoDBMapper.batchSave(logisticExpenseList);
        } catch (IOException e) {
            throw new LogisticExpenseExcelInvalidException("Fail to store excel data: " + e.getMessage());
        }
    }

    private LogisticExpense buildObject(LogisticExpensePayload logisticExpensePayload){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setCreateDatetime(LocalDateTime.now());
        logisticExpense.setCreateBy("User");
        String id = KeyMappingUtils.concatString("#",
                logisticExpensePayload.getCompanyCode(),
                logisticExpensePayload.getZoneArea(),
                logisticExpensePayload.getZoneClassPrice(),
                logisticExpensePayload.getProductGroup(),
                logisticExpensePayload.getProductSubGroup1(),
                logisticExpensePayload.getProductSubGroup2());
        logisticExpense.setId(id);
        logisticExpense.setEffectiveDate(logisticExpensePayload.getEffectiveDate());
        logisticExpense.setProductGroupName(logisticExpensePayload.getProductGroupName());
        logisticExpense.setProductSubGroup1Name(logisticExpensePayload.getProductSubGroup1Name());
        logisticExpense.setProductSubGroup2Name(logisticExpensePayload.getProductSubGroup2Name());
        logisticExpense.setLogisticExp(logisticExpensePayload.getLogisticExp());
        logisticExpense.setExpireDate(logisticExpensePayload.getExpireDate());
        logisticExpense.setStatus(logisticExpensePayload.getStatus());
        return logisticExpense;
    }

    private LogisticExpense buildObject(String key,String sortKey,LogisticExpensePayload logisticExpensePayload){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setId(key);
        logisticExpense.setEffectiveDate(sortKey);
        logisticExpense.setLastUpdateDatetime(LocalDateTime.now());
        logisticExpense.setLastUpdateBy("User");
        logisticExpense.setProductGroupName(logisticExpensePayload.getProductGroupName());
        logisticExpense.setProductSubGroup1Name(logisticExpensePayload.getProductSubGroup1Name());
        logisticExpense.setProductSubGroup2Name(logisticExpensePayload.getProductSubGroup2Name());
        logisticExpense.setLogisticExp(logisticExpensePayload.getLogisticExp());
        logisticExpense.setExpireDate(logisticExpensePayload.getExpireDate());
        logisticExpense.setStatus(logisticExpensePayload.getStatus());
        return logisticExpense;
    }
}
