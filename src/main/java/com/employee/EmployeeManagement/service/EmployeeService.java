package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.entity.DepartmentEntity;
import com.employee.EmployeeManagement.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private DepartmentRepository departmentRepository;

    public String getDepartmentNameById(Integer departmentId){
        String name=null;
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        if (departmentEntity.isPresent()){
            name = departmentEntity.get().getName();
        }
        return name;
    }
}
