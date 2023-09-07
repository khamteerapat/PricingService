package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.model.ZoneArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneAreaDataService {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    public void saveZoneArea(String zoneArea,String zoneClassPrice){
        ZoneArea zoneAreaOjb = new ZoneArea();
        zoneAreaOjb.setZoneArea(zoneArea);
        zoneAreaOjb.setZoneAreaName(zoneClassPrice);
        dynamoDBMapper.save(zoneAreaOjb);
    }
}
