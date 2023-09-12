package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.model.SalesExpense;
import com.axonstech.PricingService.payload.SalesExpensePayload;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesExpenseDataService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public SalesExpenseDataService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void saveSalesExpense(SalesExpensePayload payload) {

        SalesExpense salesExpenseOjb = new SalesExpense();

        String id = KeyMappingUtils
                .concatString("#", payload.getCompanyCode(), payload.getZoneArea(), payload.getZoneClassPrice(), payload.getProductGroup());
        salesExpenseOjb.setId(id);
        salesExpenseOjb.setZoneArea(payload.getZoneArea());
        salesExpenseOjb.setZoneClassPrice(payload.getZoneClassPrice());
        salesExpenseOjb.setProductGroup(payload.getProductGroup());
        salesExpenseOjb.setEffectiveDate(payload.getEffectiveDate());
        salesExpenseOjb.setCreateBy(payload.getCreateBy());
        salesExpenseOjb.setLastUpdateBy(payload.getLastUpdateBy());
        salesExpenseOjb.setProductGroupName(payload.getProductGroupName());
        salesExpenseOjb.setPercentSellingExp(payload.getPercentSellingExp());
        salesExpenseOjb.setPercentGaExp(payload.getPercentGaExp());
        salesExpenseOjb.setStatus(payload.getStatus());
        salesExpenseOjb.setCreateDateTime(payload.getCreateDateTime());
        salesExpenseOjb.setLastUpdateDateTime(payload.getLastUpdateDateTime());
        salesExpenseOjb.setExpireDate(payload.getExpireDate());

        dynamoDBMapper.save(salesExpenseOjb);

    }

    public List<SalesExpense> getAllSalesExpense () {
        return dynamoDBMapper.scan(SalesExpense.class, new DynamoDBScanExpression());
    }

    public SalesExpense getByIdSalesExpense (String id, String sortKey) {

        SalesExpense salesExpenseObj = new SalesExpense();
        salesExpenseObj.setId(id);
        salesExpenseObj.setEffectiveDate(sortKey);
        return dynamoDBMapper.load(SalesExpense.class, id, sortKey);

    }

    public void deleteSalesExpense(String id, String sortKey) {
        SalesExpense salesExpenseOjb = new SalesExpense();
        salesExpenseOjb.setId(id);
        salesExpenseOjb.setEffectiveDate(sortKey);
        dynamoDBMapper.delete(salesExpenseOjb);
    }

    public void updateSalesExpense (String id, String sortKey, SalesExpensePayload payload) {

        SalesExpense salesExpenseObj = new SalesExpense();

        salesExpenseObj.setId(id);
        salesExpenseObj.setEffectiveDate(sortKey);
        salesExpenseObj.setCreateBy(payload.getCreateBy());
        salesExpenseObj.setLastUpdateBy(payload.getLastUpdateBy());
        salesExpenseObj.setProductGroupName(payload.getProductGroupName());
        salesExpenseObj.setPercentSellingExp(payload.getPercentSellingExp());
        salesExpenseObj.setPercentGaExp(payload.getPercentGaExp());
        salesExpenseObj.setStatus(payload.getStatus());
        salesExpenseObj.setCreateDateTime(payload.getCreateDateTime());
        salesExpenseObj.setLastUpdateDateTime(payload.getLastUpdateDateTime());
        salesExpenseObj.setExpireDate(payload.getExpireDate());

        dynamoDBMapper.save(salesExpenseObj);

    }
}
