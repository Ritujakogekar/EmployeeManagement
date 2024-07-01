package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.dto.DepartmentDto;
import com.employee.EmployeeManagement.dto.EmployeeDto;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {

     List<EmployeeEntity> findByDepartmentId(Integer departmentId);

}
