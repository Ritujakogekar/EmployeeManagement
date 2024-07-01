package com.employee.EmployeeManagement.mapper;

import com.employee.EmployeeManagement.dto.DepartmentDto;
import com.employee.EmployeeManagement.entity.DepartmentEntity;

public class DepartmentMapper {

    public static DepartmentEntity mapDtoToEntity(DepartmentDto departmentDto){
        DepartmentEntity departmentEntity = new DepartmentEntity(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getLocation()
        );
        return departmentEntity;
    }

    public static DepartmentDto mapEntityToDto(DepartmentEntity departmentEntity){
        DepartmentDto departmentDto = new DepartmentDto(
                departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getLocation()
        );
        return departmentDto;
    }
}
