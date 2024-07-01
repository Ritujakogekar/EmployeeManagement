package com.employee.EmployeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDto {

    private int id;
    private String name;
    private String location;
}
