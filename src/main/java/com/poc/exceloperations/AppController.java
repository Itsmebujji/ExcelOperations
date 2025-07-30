package com.poc.exceloperations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@Tag(name = "Excel Operations API", description = "Operations related to excel")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "upload excel data to DB")
    public String uploadData(
            @Parameter(description = "Excel file to upload", required = true)
            @RequestPart(name = "file")MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("Please upload the .xlsx file only").toString();
        }
        appService.uploadExcelData(file);
        return ResponseEntity.ok("File is uploaded and data is saved successfully").toString();
    }

    @GetMapping(value = "/exprot/data", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @Operation(summary = "export the data in file")
    public ResponseEntity<InputStreamResource> exportEmployeesData(){
        ByteArrayInputStream in = appService.exportEmployeesData();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=employees.xlsx");
        return ResponseEntity.ok().headers(headers).contentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @PostMapping(value = "/upload/xmldata", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "xml data upload to db")
    public ResponseEntity<String> uploadData(@RequestBody Employee employee){
        if(employee==null){
            return ResponseEntity.badRequest().build();
        }
        appService.uploadData(employee);
        return ResponseEntity.ok("Data is uploaded successfully");
    }

    @PostMapping(value = "/get/xmldata/employee/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "DB data converts to xml")
    public ResponseEntity<Object> getXmlData(
            @PathVariable(name = "id") int id
    ){
        if(id==0){
            return ResponseEntity.badRequest().body("Provide Valid Employee Id");
        }
        Employee emp = appService.getXmlData(id);
        return ResponseEntity.ok(emp);
    }

}
