package com.poc.exceloperations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Employee Details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Name of the Employee", example = "yalamanchi vineethkumar")
    private String name;
    @Schema(description = "Email of the Employee", example = "vk@gmail.com")
    private String email;
    @Schema(description = "Department of the Employee", example = "IT")
    private String department;

    public EmployeeDetails(){}
    public EmployeeDetails(Long id, String name, String email, String department){
        this.id =id;
        this.name=name;
        this.email=email;
        this.department=department;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getDepartment(){
        return department;
    }

    public void setId(Long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setDepartment(String department){
        this.department=department;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
