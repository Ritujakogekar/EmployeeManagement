package com.employee.EmployeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String name;
    private String email;
    private String position;
    private double salary;
    private int departmentId;
    private String departmentName;

}
