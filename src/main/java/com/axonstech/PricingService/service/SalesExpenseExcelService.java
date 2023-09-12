package com.axonstech.PricingService.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.axonstech.PricingService.enumeration.SalesExpenseExcelColumnEnum;
import com.axonstech.PricingService.exceptions.LogisticExpenseByProductExcelInvalidException;
import com.axonstech.PricingService.exceptions.SalesExpenseExcelInvalidException;
import com.axonstech.PricingService.model.LogisticExpenseByProduct;
import com.axonstech.PricingService.model.SalesExpense;
import com.axonstech.PricingService.payload.SalesExpensePayload;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import com.axonstech.PricingService.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class SalesExpenseExcelService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public SalesExpenseExcelService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void extract(InputStream inputStream, String companyCode) throws IOException {
        try (inputStream) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : xssfWorkbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                Row selectRow = null;

                if (rowIterator.hasNext()) {
                    selectRow = rowIterator.next();
                }

                if (selectRow == null) {
                    throw new SalesExpenseExcelInvalidException();
                } else {
                    processExcelAndSave(rowIterator, companyCode);
                }
            }
        }
    }

    private void processExcelAndSave(Iterator<Row> rowIterator, String companyCode) {
        LogUtils.beginInfo(log, "Begin Extract Excel");
        List<SalesExpense> salesExpenses = new ArrayList<>();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            toBeProcessingRow(row,salesExpenses,companyCode);
        }
        LogUtils.endInfo(log, "End Extract Excel");
        LogUtils.beginInfo(log, "Begin Save");
        dynamoDBMapper.batchSave(salesExpenses);
    }

    private void toBeProcessingRow(Row row, List<SalesExpense> salesExpenseList, String companyCode) {
        SalesExpense salesExpense = new SalesExpense();
        salesExpense.setCompanyCode(companyCode);

        Cell zoneAreaCell = row.getCell(SalesExpenseExcelColumnEnum.ZONE_AREA.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneArea = zoneAreaCell.getStringCellValue();
        salesExpense.setZoneArea(zoneArea);

        Cell zoneClassPriceCell = row.getCell(SalesExpenseExcelColumnEnum.ZONE_CLASS_PRICE.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneClassPrice = zoneClassPriceCell.getStringCellValue();
        salesExpense.setZoneClassPrice(zoneClassPrice);

        Cell effectiveDateCell = row.getCell(SalesExpenseExcelColumnEnum.EFFECTIVE_DATE.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String effectiveDate = null;

        if (effectiveDateCell.getCellType() == CellType.NUMERIC) {
            Date datevalue = effectiveDateCell.getDateCellValue();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            effectiveDate = dateFormat.format(datevalue);
        } else if (effectiveDateCell.getCellType() == CellType.STRING) {
            effectiveDate = effectiveDateCell.getStringCellValue();
        }
        salesExpense.setEffectiveDate(effectiveDate);

        Cell productGroupCell = row.getCell(SalesExpenseExcelColumnEnum.PRODUCT_GROUP.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroup = productGroupCell.getStringCellValue();
        salesExpense.setProductGroup(productGroup);

        Cell productGroupNameCell = row.getCell(SalesExpenseExcelColumnEnum.PRODUCT_GROUP_NAME.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroupName = productGroupNameCell.getStringCellValue();
        salesExpense.setProductGroup(productGroupName);

        Cell percentSellingExpCell = row.getCell(SalesExpenseExcelColumnEnum.PERCENT_SELLING_EXP.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Double percentSellingExp = percentSellingExpCell.getNumericCellValue();
        salesExpense.setPercentSellingExp(percentSellingExp);

        Cell percentGaExpCell = row.getCell(SalesExpenseExcelColumnEnum.PERCENT_GA_EXP.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Double percentGaExp = percentGaExpCell.getNumericCellValue();
        salesExpense.setPercentGaExp(percentGaExp);

        Cell statusCell = row.getCell(SalesExpenseExcelColumnEnum.STATUS.getIndex(),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String status = statusCell.getStringCellValue();
        salesExpense.setStatus(status);

        String id = KeyMappingUtils.concatString("#", zoneArea,zoneClassPrice,productGroup);
        salesExpense.setId(id);

        salesExpenseList.add(salesExpense);

    }
}
