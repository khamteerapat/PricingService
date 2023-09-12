package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.exceptions.LogisticExpenseExcelInvalidException;
import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.payload.LogisticExpensePayload;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogisticExpenseService {
    private final LogisticExpenseExcelService excelService;
    private final DynamoDBMapper dynamoDBMapper;

    public LogisticExpenseService(LogisticExpenseExcelService excelService, DynamoDBMapper dynamoDBMapper) {
        this.excelService = excelService;
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public List<LogisticExpense> getAllLogisticExpense(){
        DynamoDBScanExpression dbScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(LogisticExpense.class,dbScanExpression);
    }

    public LogisticExpense getByKeyLogisticExpense(String key,String sortKey){
        LocalDate localDate = LocalDate.parse(sortKey);
        return dynamoDBMapper.load(LogisticExpense.class,key,localDate);
    }
    public void createLogisticExpense(String user, String companyCode, LogisticExpensePayload logisticExpensePayload){
        dynamoDBMapper.save(buildObject(user,companyCode,logisticExpensePayload,"CREATE"));
    }
    public void updateLogisticExpense(String user, String companyCode, LogisticExpensePayload logisticExpensePayload){
        DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .build();
        dynamoDBMapper.save(buildObject(user,companyCode,logisticExpensePayload,"UPDATE"), dynamoDBMapperConfig);
    }
    public void deleteLogisticExpense(String key, LocalDate sortKey){
        LogisticExpense logisticExpense = new LogisticExpense();
        logisticExpense.setId(key);
        logisticExpense.setEffectiveDate(sortKey);
        dynamoDBMapper.delete(logisticExpense);
    }
    public void uploadLogisticExpense(String user, String companyCode, MultipartFile file){
        try {
            List<LogisticExpense> logisticByProductList = new ArrayList<>();
            excelService.excelToObject(user,companyCode,logisticByProductList,file.getInputStream());
            dynamoDBMapper.batchSave(logisticByProductList);
        } catch (IOException e) {
            throw new LogisticExpenseExcelInvalidException("Fail to store excel data: " + e.getMessage());
        }
    }
    private LogisticExpense buildObject(String user, String companyCode,LogisticExpensePayload payload,String action){
        LogisticExpense logisticExpense = new LogisticExpense();
        if(action.equals("CREATE")){
            logisticExpense.setCreateDatetime(LocalDateTime.now());
            logisticExpense.setCreateBy(user);
        } else if (action.equals("UPDATE")) {
            logisticExpense.setLastUpdateDatetime(LocalDateTime.now());
            logisticExpense.setLastUpdateBy(user);
        }
        String id = KeyMappingUtils.concatString("#",
                companyCode,
                payload.getZoneArea(),
                payload.getZoneClassPrice(),
                payload.getProductGroup(),
                payload.getProductSubGroup1(),
                payload.getProductSubGroup2());
        logisticExpense.setId(id);
        logisticExpense.setEffectiveDate(payload.getEffectiveDate());
        logisticExpense.setProductGroupName(payload.getProductGroupName());
        logisticExpense.setProductSubGroup1Name(payload.getProductSubGroup1Name());
        logisticExpense.setProductSubGroup2Name(payload.getProductSubGroup2Name());
        logisticExpense.setLogisticExp(payload.getLogisticExp());
        logisticExpense.setExpireDate(payload.getExpireDate());
        logisticExpense.setStatus(payload.getStatus());
        return logisticExpense;
    }
}
