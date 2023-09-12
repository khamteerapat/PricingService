package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.axonstech.PricingService.enumeration.LogisticExpenseByProductExcelColumnEnum;
import com.axonstech.PricingService.exceptions.LogisticExpenseByProductExcelInvalidException;
import com.axonstech.PricingService.model.LogisticExpenseByProduct;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import com.axonstech.PricingService.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class LogisticExpenseByProductExcelService {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void extract(InputStream inputStream,String companyCode) throws IOException {
        try (inputStream) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : xssfWorkbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                Row selectRow = null;

                if (rowIterator.hasNext()) {
                    selectRow = rowIterator.next();
                }

                if (selectRow == null) {//todo check Invalid Import Excel File
                    throw new LogisticExpenseByProductExcelInvalidException();
                } else {
                    //todo Process Excel
                    processExcelAndSave(rowIterator, companyCode);
                }
            }
        }
    }

    private void processExcelAndSave(Iterator<Row> rowIterator,String companyCode){
        LogUtils.beginInfo(log,"Begin Extract Excel");
        List<LogisticExpenseByProduct> logisticByProductList = new ArrayList<>();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            toBeProcessingRow(row,logisticByProductList,companyCode);
        }
        LogUtils.endInfo(log,"End Extract Excel");
        LogUtils.beginInfo(log,"Begin Save");
        dynamoDBMapper.batchSave(logisticByProductList);
        LogUtils.endInfo(log,"End Save");
    }

    private void toBeProcessingRow(Row row,List<LogisticExpenseByProduct> logisticByProductList,String companyCode){
        LogisticExpenseByProduct logisticByProduct = new LogisticExpenseByProduct();
        logisticByProduct.setCompanyCode(companyCode);


        Cell zoneAreaCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.ZONE_AREA.getIndex(),Row.CREATE_NULL_AS_BLANK);
        String zoneArea = zoneAreaCell.getStringCellValue();
        logisticByProduct.setZoneArea(zoneArea);

        Cell zoneClassPriceCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.ZONE_CLASS_PRICE.getIndex(),Row.CREATE_NULL_AS_BLANK);
        String zoneClassPrice = zoneClassPriceCell.getStringCellValue();
        logisticByProduct.setZoneClassPrice(zoneClassPrice);

        Cell effectiveDateCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.EFFECTIVE_DATE.getIndex(),Row.CREATE_NULL_AS_BLANK);
        Date effectiveDate = effectiveDateCell.getDateCellValue();
        logisticByProduct.setEffectiveDate(effectiveDate);

        Cell productCodeCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.PRODUCT_CODE.getIndex(),Row.CREATE_NULL_AS_BLANK);
        String productCode = productCodeCell.getStringCellValue();
        logisticByProduct.setProductCode(productCode);

        Cell logisticExpCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.LOGISTIC_EXP.getIndex(),Row.CREATE_NULL_AS_BLANK);
        Double logisticExp = logisticExpCell.getNumericCellValue();
        logisticByProduct.setLogisticExp(logisticExp);

        Cell statusCell = row.getCell(LogisticExpenseByProductExcelColumnEnum.STATUS.getIndex(),Row.CREATE_NULL_AS_BLANK);
        String status = statusCell.getStringCellValue();
        logisticByProduct.setStatus(status);

        String id = KeyMappingUtils.concatString("#",zoneArea,zoneClassPrice,productCode);
        logisticByProduct.setId(id);

        logisticByProductList.add(logisticByProduct);
    }
}
