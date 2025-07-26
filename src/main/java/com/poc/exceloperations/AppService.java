package com.poc.exceloperations;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppService {

    private final AppRepository appRepository;
    private final ExcelHelper excelHelper;

    public AppService(ExcelHelper excelHelper, AppRepository appRepository) {
        this.excelHelper = excelHelper;
        this.appRepository = appRepository;
    }

    public void uploadExcelData(MultipartFile file) {
        try{
            List<EmployeeDetails> employees = excelHelper.excelToEmployees(file.getInputStream());
            appRepository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the data: "+e.getMessage());
        }
    }
}
