package com.poc.exceloperations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            return new ResponseEntity<>("Please upload the .xlsx file only",HttpStatus.BAD_REQUEST).toString();
        }
        appService.uploadExcelData(file);
        return new ResponseEntity<>("File is uploaded and data is saved successfully", HttpStatus.OK).toString();
    }
}
