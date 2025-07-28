package com.poc.exceloperations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class ExcelHelper {
    public List<EmployeeDetails> excelToEmployees(InputStream in){
        List<EmployeeDetails> employees = new ArrayList<>();
        try{
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            int rowNumber = 0;
            while(rows.hasNext()){
                Row currentRow = rows.next();
                if(rowNumber++==0) continue;
                EmployeeDetails emp = new EmployeeDetails();
                emp.setName(currentRow.getCell(0).getStringCellValue());
                emp.setEmail(currentRow.getCell(1).getStringCellValue());
                emp.setDepartment(currentRow.getCell(2).getStringCellValue());
                employees.add(emp);
            }
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse the file: "+e.getMessage());
        }
        return employees;
    }

    public ByteArrayInputStream exportEmployeesData(List<EmployeeDetails> employeeDetails) {
        try(Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream()
                ){
            Sheet sheet = workbook.createSheet("Employees");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID","Name","Email","Department"};
            for (int i=0; i<headers.length;i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            int rowIdx = 1;
            for(EmployeeDetails emp : employeeDetails){
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getName());
                row.createCell(2).setCellValue(emp.getEmail());
                row.createCell(3).setCellValue(emp.getDepartment());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel"+e.getMessage());
        }
    }
}
