package com.axonstech.PricingService.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.axonstech.PricingService.model.ZoneArea;
import com.axonstech.PricingService.payload.ZoneAreaRequestPayload;
import com.axonstech.PricingService.service.ZoneAreaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/zonearea")
public class ZoneAreaApi {
    @Autowired
    private ZoneAreaDataService zoneAreaDataService;


    @PostMapping("/save")
    public String saveZoneArea(@RequestBody ZoneAreaRequestPayload zoneAreaRequestPayload){
        zoneAreaDataService.saveZoneArea(zoneAreaRequestPayload.getZoneArea(), zoneAreaRequestPayload.getZoneAreaName());
        return "Success";
    }

    @PostMapping("/list")
    public List<ZoneArea> getZoneAreaList(){
        //todo select data from dynamodb ZoneArea Table
        return new ArrayList<>();
    }
}
