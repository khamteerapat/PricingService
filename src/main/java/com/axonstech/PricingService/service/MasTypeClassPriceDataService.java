package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.axonstech.PricingService.model.MasTypeClassPrice;
import com.axonstech.PricingService.model.StandardDataModel;
import com.axonstech.PricingService.payload.MasTypeClassPricePayload;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasTypeClassPriceDataService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public MasTypeClassPriceDataService(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void saveMasTypeClassPrice(MasTypeClassPricePayload masTypeClassPricePayload) {

        MasTypeClassPrice masTypeClassPriceOjb = new MasTypeClassPrice();

        masTypeClassPriceOjb.setCreateBy(masTypeClassPricePayload.getCreateBy());
        masTypeClassPriceOjb.setCreateDatetime(masTypeClassPricePayload.getCreateDatetime());
        masTypeClassPriceOjb.setLastUpdateBy(masTypeClassPricePayload.getLastUpdateBy());
        masTypeClassPriceOjb.setLastUpdateDatetime(masTypeClassPricePayload.getLastUpdateDatetime());
        masTypeClassPriceOjb.setZoneArea(masTypeClassPricePayload.getZoneArea());
        masTypeClassPriceOjb.setZoneClassPrice(masTypeClassPricePayload.getZoneClassPrice());
        masTypeClassPriceOjb.setVendorGroup(masTypeClassPricePayload.getVendorGroup());
        masTypeClassPriceOjb.setTypeClassPrice(masTypeClassPricePayload.getTypeClassPrice());
        masTypeClassPriceOjb.setZoneAreaName(masTypeClassPricePayload.getZoneAreaName());
        masTypeClassPriceOjb.setZoneClassPriceName(masTypeClassPricePayload.getZoneClassPriceName());
        masTypeClassPriceOjb.setVendorGroupName(masTypeClassPricePayload.getVendorGroupName());
        masTypeClassPriceOjb.setTypeClassPriceName(masTypeClassPricePayload.getTypeClassPriceName());
        masTypeClassPriceOjb.setStatus(masTypeClassPricePayload.getStatus());

        String sortKey = KeyMappingUtils.concatString("#",
                        masTypeClassPricePayload.getZoneArea(),
                        masTypeClassPricePayload.getZoneClassPrice(),
                        masTypeClassPricePayload.getTypeClassPrice());
        masTypeClassPriceOjb.setSortId(sortKey);

        dynamoDBMapper.save(masTypeClassPriceOjb);

    }

    public List<MasTypeClassPrice> getAllMasTypeClassPrice() {
        return dynamoDBMapper.scan(MasTypeClassPrice.class, new DynamoDBScanExpression());
    }

    public MasTypeClassPrice getByIdMasTypeClassPrice(String id, String sortKey) {

        MasTypeClassPrice masTypeClassPriceObj = new MasTypeClassPrice();
        masTypeClassPriceObj.setVendorGroup(id);
        masTypeClassPriceObj.setSortId(sortKey);
        return  dynamoDBMapper.load(MasTypeClassPrice.class, id, sortKey);
    }

    public void deleteMasTypeClassPrice(String id, String sortKey) {

        MasTypeClassPrice masTypeClassPriceOjb = new MasTypeClassPrice();
        masTypeClassPriceOjb.setVendorGroup(id);
        masTypeClassPriceOjb.setSortId(sortKey);
        dynamoDBMapper.delete(masTypeClassPriceOjb);
    }

    public void updateMasTypeClassPrice(String id, String sortKey, MasTypeClassPricePayload masTypeClassPricePayload) {

        MasTypeClassPrice masTypeClassPriceObj = new MasTypeClassPrice();

        masTypeClassPriceObj.setVendorGroup(id);
        masTypeClassPriceObj.setSortId(sortKey);
        masTypeClassPriceObj.setCreateBy(masTypeClassPricePayload.getCreateBy());
        masTypeClassPriceObj.setCreateDatetime(masTypeClassPricePayload.getCreateDatetime());
        masTypeClassPriceObj.setLastUpdateBy(masTypeClassPricePayload.getLastUpdateBy());
        masTypeClassPriceObj.setLastUpdateDatetime(masTypeClassPricePayload.getLastUpdateDatetime());
        masTypeClassPriceObj.setZoneArea(masTypeClassPricePayload.getZoneArea());
        masTypeClassPriceObj.setZoneClassPrice(masTypeClassPricePayload.getZoneClassPrice());
        masTypeClassPriceObj.setTypeClassPrice(masTypeClassPricePayload.getTypeClassPrice());
        masTypeClassPriceObj.setZoneAreaName(masTypeClassPricePayload.getZoneAreaName());
        masTypeClassPriceObj.setZoneClassPriceName(masTypeClassPricePayload.getZoneClassPriceName());
        masTypeClassPriceObj.setVendorGroupName(masTypeClassPricePayload.getVendorGroupName());
        masTypeClassPriceObj.setTypeClassPriceName(masTypeClassPricePayload.getTypeClassPriceName());
        masTypeClassPriceObj.setStatus(masTypeClassPricePayload.getStatus());

        dynamoDBMapper.save(masTypeClassPriceObj);

    }
}
