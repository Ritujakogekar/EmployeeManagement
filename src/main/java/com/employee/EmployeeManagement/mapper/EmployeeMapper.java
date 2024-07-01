package com.employee.EmployeeManagement.mapper;

import com.employee.EmployeeManagement.dto.EmployeeDto;
import com.employee.EmployeeManagement.entity.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeEntity mapDtotoEntity(EmployeeDto employeeDto){
        EmployeeEntity employeeEntity=new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getPosition(),
                employeeDto.getSalary(),
                employeeDto.getDepartmentId(),
                employeeDto.getDepartmentName()
        );
        return  employeeEntity;
    }

    public  static EmployeeDto mapEntitytoDto(EmployeeEntity employeeEntity){
        EmployeeDto employeeDto=new EmployeeDto(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getEmail(),
                employeeEntity.getPosition(),
                employeeEntity.getSalary(),
                employeeEntity.getDepartmentId(),
                employeeEntity.getDepartmentName()
        );
        return  employeeDto;
    }
}
