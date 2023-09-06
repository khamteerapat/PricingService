package com.axonstech.PricingService.api;

import com.axonstech.PricingService.payload.ZoneAreaRequestPayload;
import com.axonstech.PricingService.service.ZoneAreaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
