package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.axonstech.PricingService.enumeration.MasTypeClassPriceExcelColumnEnum;
import com.axonstech.PricingService.exceptions.MasTypeClassPriceExcelInvalidException;
import com.axonstech.PricingService.model.MasTypeClassPrice;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import com.axonstech.PricingService.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class MasTypeClassPriceExcelService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public MasTypeClassPriceExcelService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void extract(InputStream inputStream) throws IOException {
        try(inputStream) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : xssfWorkbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                Row selectRow = null;

                if (rowIterator.hasNext()) {
                    selectRow = rowIterator.next();
                }

                if (selectRow == null) {
                    throw new MasTypeClassPriceExcelInvalidException();
                } else {
                    processExcelAndSave(rowIterator);
                }
            }
        }
    }

    private void processExcelAndSave(Iterator<Row> rowIterator) {
        LogUtils.beginInfo(log, "Begin Extract Excel");
        List<MasTypeClassPrice> masTypeClassPriceList = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            tobeProcessingRow(row, masTypeClassPriceList);

        }
        LogUtils.endInfo(log, "End Extract Excel");
        LogUtils.beginInfo(log, "Begin Save");
        dynamoDBMapper.batchSave(masTypeClassPriceList);

    }

    private void tobeProcessingRow(Row row, List<MasTypeClassPrice> masTypeClassPriceList) {

        MasTypeClassPrice masTypeClassPrice = new MasTypeClassPrice();

        Cell zoneAreaCell = row.getCell(MasTypeClassPriceExcelColumnEnum.ZONE_AREA.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneArea = zoneAreaCell.getStringCellValue();
        masTypeClassPrice.setZoneArea(zoneArea);

        Cell zoneClassPriceCell = row.getCell(MasTypeClassPriceExcelColumnEnum.ZONE_CLASS_PRICE.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneClassPrice = zoneClassPriceCell.getStringCellValue();
        masTypeClassPrice.setZoneClassPrice(zoneClassPrice);

        Cell vendorGroupCell = row.getCell(MasTypeClassPriceExcelColumnEnum.VENDOR_GROUP.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String vendorGroup = vendorGroupCell.getStringCellValue();
        masTypeClassPrice.setVendorGroup(vendorGroup);

        Cell typeClassPriceCell = row.getCell(MasTypeClassPriceExcelColumnEnum.TYPE_CLASS_PRICE.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String typeClassPrice = typeClassPriceCell.getStringCellValue();
        masTypeClassPrice.setTypeClassPrice(typeClassPrice);

        Cell zoneAreaNameCell = row.getCell(MasTypeClassPriceExcelColumnEnum.ZONE_AREA_NAME.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneAreaName = zoneAreaNameCell.getStringCellValue();
        masTypeClassPrice.setZoneAreaName(zoneAreaName);

        Cell zoneClassPriceNameCell = row.getCell(MasTypeClassPriceExcelColumnEnum.ZONE_CLASS_PRICE_NAME.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneClassPriceName = zoneClassPriceNameCell.getStringCellValue();
        masTypeClassPrice.setZoneClassPriceName(zoneClassPriceName);

        Cell vendorGroupNameCell = row.getCell(MasTypeClassPriceExcelColumnEnum.VENDOR_GROUP_NAME.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String vendorGroupName = vendorGroupNameCell.getStringCellValue();
        masTypeClassPrice.setVendorGroupName(vendorGroupName);

        Cell typeClassPriceNameCell = row.getCell(MasTypeClassPriceExcelColumnEnum.TYPE_CLASS_PRICE_NAME.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String typeClassPriceName = typeClassPriceNameCell.getStringCellValue();
        masTypeClassPrice.setTypeClassPriceName(typeClassPriceName);

        Cell statusCell = row.getCell(MasTypeClassPriceExcelColumnEnum.STATUS.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String status = statusCell.getStringCellValue();
        masTypeClassPrice.setStatus(status);

        String sortKey = KeyMappingUtils.concatString("#", zoneArea, zoneClassPrice, typeClassPrice);
        masTypeClassPrice.setSortId(sortKey);

        masTypeClassPriceList.add(masTypeClassPrice);

    }
}
