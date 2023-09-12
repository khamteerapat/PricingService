package com.axonstech.PricingService.utils;

import com.axonstech.PricingService.exceptions.LogisticExpenseExcelInvalidException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public abstract class ExcelExtract<T>{
    protected int numberColumn;

    protected ExcelExtract(int numberColumn) {
        this.numberColumn = numberColumn;
    }

    public void excelToObject(String user, String companyCode, List<T> list, InputStream is){
        try(is) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            for (Sheet sheet : xssfWorkbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                Row selectRow = null;

                if (rowIterator.hasNext()) {
                    selectRow = rowIterator.next();
                }

                if (selectRow == null || selectRow.getPhysicalNumberOfCells() != numberColumn) {
                    throw new LogisticExpenseExcelInvalidException("Excel File Invalid!");
                } else {
                    processExcelAndSave(user,companyCode,rowIterator,list);
                }
            }
            xssfWorkbook.close();
        } catch (IOException e) {
            throw new LogisticExpenseExcelInvalidException(e.getMessage());
        }
    }

    public void processExcelAndSave(String user, String companyCode, Iterator<Row> rowIterator,List<T> list){
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            toBeProcessingRow(user,companyCode,row,list);
        }
    }

    protected abstract void toBeProcessingRow(String user, String companyCode, Row row, List<T> logisticByProductList);
}
