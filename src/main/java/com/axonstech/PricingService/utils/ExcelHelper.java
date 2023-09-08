package com.axonstech.PricingService.utils;

import com.axonstech.PricingService.enumeration.LogisticExpenseExcelColumnEnum;
import com.axonstech.PricingService.exceptions.LogisticExpenseExcelInvalidException;
import com.axonstech.PricingService.model.LogisticExpense;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private ExcelHelper(){}
    public static boolean hasExcelFormat(MultipartFile file) {
        String type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        return type.equals(file.getContentType());
    }
    public static List<LogisticExpense> excelToLogisticExpense(InputStream is){
        try(is) {
            List<LogisticExpense> logisticByProductList = new ArrayList<>();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            for (Sheet sheet : xssfWorkbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                Row selectRow = null;

                if (rowIterator.hasNext()) {
                    selectRow = rowIterator.next();
                }

                if (selectRow == null) {
                    throw new LogisticExpenseExcelInvalidException();
                } else {
                    processExcelAndSave(rowIterator,logisticByProductList);
                }
            }
            xssfWorkbook.close();
            return logisticByProductList;
        } catch (IOException e) {
            throw new LogisticExpenseExcelInvalidException(e.getMessage());
        }
    }

    private static void processExcelAndSave(Iterator<Row> rowIterator,List<LogisticExpense> logisticByProductList){
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            toBeProcessingRow(row,logisticByProductList);
        }
    }
    private static void toBeProcessingRow(Row row, List<LogisticExpense> logisticByProductList){
        LogisticExpense logisticExpense = new LogisticExpense();

        logisticExpense.setCreateDatetime(LocalDateTime.now());
        logisticExpense.setCreateBy("User");

        Cell companyCodeCell = row.getCell(LogisticExpenseExcelColumnEnum.COMPANY_CODE.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String companyCode = companyCodeCell.getStringCellValue();
        logisticExpense.setCompanyCode(companyCode);

        Cell zoneAreaCell = row.getCell(LogisticExpenseExcelColumnEnum.ZONE_AREA.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneArea = zoneAreaCell.getStringCellValue();
        logisticExpense.setZoneArea(zoneArea);

        Cell zoneClassPriceCell = row.getCell(LogisticExpenseExcelColumnEnum.ZONE_CLASS_PRICE.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String zoneClassPrice = zoneClassPriceCell.getStringCellValue();
        logisticExpense.setZoneClassPrice(zoneClassPrice);

        Cell productGroupCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_GROUP.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroup = productGroupCell.getStringCellValue();
        logisticExpense.setProductGroup(productGroup);

        Cell productSubGroup1Cell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP1.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup1 = productSubGroup1Cell.getStringCellValue();
        logisticExpense.setProductSubGroup1(productSubGroup1);

        Cell productSubGroup2Cell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP2.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup2 = productSubGroup2Cell.getStringCellValue();
        logisticExpense.setProductSubGroup2(productSubGroup2);

        Cell effectiveDateCell = row.getCell(LogisticExpenseExcelColumnEnum.EFFECTIVE_DATE.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String effectiveDate = effectiveDateCell.getStringCellValue();
        logisticExpense.setEffectiveDate(effectiveDate);

        Cell productGroupNameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_GROUP_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productGroupName = productGroupNameCell.getStringCellValue();
        logisticExpense.setProductGroupName(productGroupName);

        Cell productSubGroup1NameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP1_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup1Name = productSubGroup1NameCell.getStringCellValue();
        logisticExpense.setProductSubGroup1Name(productSubGroup1Name);

        Cell productSubGroup2NameCell = row.getCell(LogisticExpenseExcelColumnEnum.PRODUCT_SUB_GROUP2_NAME.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String productSubGroup2Name = productSubGroup2NameCell.getStringCellValue();
        logisticExpense.setProductSubGroup2Name(productSubGroup2Name);

        Cell logisticExpCell = row.getCell(LogisticExpenseExcelColumnEnum.LOGISTIC_EXP.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Double logisticExp = logisticExpCell.getNumericCellValue();
        logisticExpense.setLogisticExp(logisticExp);

        Cell statusCell = row.getCell(LogisticExpenseExcelColumnEnum.STATUS.getIndex(),
                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String status = statusCell.getStringCellValue();
        logisticExpense.setStatus(status);

        String id = KeyMappingUtils.concatString("#",companyCode,zoneArea,zoneClassPrice,productGroup,productSubGroup1,productSubGroup2);
        logisticExpense.setId(id);

        logisticByProductList.add(logisticExpense);
    }
}
