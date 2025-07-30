package com.poc.exceloperations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    public ByteArrayInputStream exportEmployeesData() {
        List<EmployeeDetails> employeeDetails = appRepository.findAll();
        return excelHelper.exportEmployeesData(employeeDetails);
    }

    public void uploadData(Employee employee) {
        try{
            EmployeeDetails emp = new EmployeeDetails(
                    employee.getName(), employee.getEmail(), employee.getDepartment()
            );
            appRepository.save(emp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Employee getXmlData(int id) {
        EmployeeDetails emp = appRepository.findById(id).orElse(null);
        if(emp==null){
            return null;
        }
        Employee employee = new Employee();
        employee.setId(emp.getId());
        employee.setName(emp.getName());
        employee.setEmail(emp.getEmail());
        employee.setDepartment(emp.getDepartment());
        return employee;
    }
}
