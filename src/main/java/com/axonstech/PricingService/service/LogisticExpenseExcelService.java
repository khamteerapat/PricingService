package com.axonstech.PricingService.service;

import com.axonstech.PricingService.enumeration.LogisticExpenseExcelColumnEnum;
import com.axonstech.PricingService.model.LogisticExpense;
import com.axonstech.PricingService.utils.ExcelExtract;
import com.axonstech.PricingService.utils.KeyMappingUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class LogisticExpenseExcelService extends ExcelExtract<LogisticExpense> {
    private static final int NUMBER_COLUMN = 11;
    protected LogisticExpenseExcelService() {
        super(NUMBER_COLUMN);
    }
    @Override
    protected void toBeProcessingRow(String user, String companyCode, Row row, List<LogisticExpense> logisticByProductList) {
        LogisticExpense logisticExpense = new LogisticExpense();
        DataFormatter formatter = new DataFormatter();

        logisticExpense.setCreateDatetime(LocalDateTime.now());
        logisticExpense.setCreateBy(user);

        logisticExpense.setCompanyCode(companyCode);

        Cell zoneAreaCell = row.getCell(LogisticExpenseExcelColumnEnum.ZONE_AREA.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneArea = formatter.formatCellValue(zoneAreaCell);
        logisticExpense.setZoneArea(zoneArea);

        Cell zoneClassPriceCell = row.getCell(LogisticExpenseExcelColumnEnum.ZONE_CLASS_PRICE.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneClassPrice = formatter.formatCellValue(zoneClassPriceCell);
        logisticExpense.setZoneClassPrice(zoneClassPrice);

        Cell productGroupCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_GROUP.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroup = formatter.formatCellValue(productGroupCell);
        logisticExpense.setProductGroup(productGroup);

        Cell productSubGroup1Cell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP1.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup1 = formatter.formatCellValue(productSubGroup1Cell);
        logisticExpense.setProductSubGroup1(productSubGroup1);

        Cell productSubGroup2Cell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP2.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup2 = formatter.formatCellValue(productSubGroup2Cell);
        logisticExpense.setProductSubGroup2(productSubGroup2);

        Cell effectiveDateCell = row.getCell(LogisticExpenseExcelColumnEnum.EFFECTIVE_DATE.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        LocalDate effectiveDate = LocalDate.parse(formatter.formatCellValue(effectiveDateCell),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        logisticExpense.setEffectiveDate(effectiveDate);

        Cell productGroupNameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_GROUP_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroupName = formatter.formatCellValue(productGroupNameCell);
        logisticExpense.setProductGroupName(productGroupName);

        Cell productSubGroup1NameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP1_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup1Name = formatter.formatCellValue(productSubGroup1NameCell);
        logisticExpense.setProductSubGroup1Name(productSubGroup1Name);

        Cell productSubGroup2NameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP2_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup2Name = formatter.formatCellValue(productSubGroup2NameCell);
        logisticExpense.setProductSubGroup2Name(productSubGroup2Name);

        Cell logisticExpCell = row.getCell(LogisticExpenseExcelColumnEnum.LOGISTIC_EXP.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Double logisticExp = logisticExpCell.getNumericCellValue();
        logisticExpense.setLogisticExp(logisticExp);

        Cell statusCell = row.getCell(LogisticExpenseExcelColumnEnum.STATUS.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String status = formatter.formatCellValue(statusCell);
        logisticExpense.setStatus(status);

        String id = KeyMappingUtils.concatString("#",companyCode,zoneArea,zoneClassPrice,productGroup,productSubGroup1,productSubGroup2);
        logisticExpense.setId(id);

        logisticByProductList.add(logisticExpense);
    }
}
