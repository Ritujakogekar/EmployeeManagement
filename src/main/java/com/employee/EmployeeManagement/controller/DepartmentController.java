package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.dto.DepartmentDto;
import com.employee.EmployeeManagement.dto.EmployeeDto;
import com.employee.EmployeeManagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class DepartmentController {

    private DepartmentService departmentService;

    @CrossOrigin
    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDto));
    }

    @CrossOrigin
    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(departmentService.addEmployee(employeeDto));
    }

    @CrossOrigin
    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(departmentService.getAllEmployees());
    }

    @CrossOrigin
    @GetMapping("/get-all-departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartents());
    }

    @CrossOrigin
    @GetMapping("/get-employees/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartmentId(@PathVariable("departmentId") Integer departmentId) {

        return ResponseEntity.ok(departmentService.getEmployeeByDepartmentId(departmentId));
    }

    @CrossOrigin
    @GetMapping("/get-employee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId) {
        EmployeeDto employeeDto = departmentService.getEmployeeByID(employeeId);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/update-employee/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Integer employeeId, @RequestBody EmployeeDto updatedEmployeeDto) {
        EmployeeDto updatedEmployee = departmentService.updateEmployeeById(employeeId, updatedEmployeeDto);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.deleteEmployeeById(id));

    }
}
