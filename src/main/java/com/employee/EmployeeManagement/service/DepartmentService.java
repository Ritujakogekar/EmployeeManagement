package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.DepartmentDto;
import com.employee.EmployeeManagement.dto.EmployeeDto;
import com.employee.EmployeeManagement.entity.DepartmentEntity;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.mapper.DepartmentMapper;
import com.employee.EmployeeManagement.mapper.EmployeeMapper;
import com.employee.EmployeeManagement.repository.DepartmentRepository;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    public DepartmentDto addDepartment(DepartmentDto departmentDto){
        DepartmentEntity departmentEntity = DepartmentMapper.mapDtoToEntity(departmentDto);
        DepartmentEntity departmentEntity1 = departmentRepository.save(departmentEntity);
        DepartmentDto departmentDto1 = DepartmentMapper.mapEntityToDto(departmentEntity1);
        return departmentDto1;
    }
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        EmployeeEntity employeeEntity= EmployeeMapper.mapDtotoEntity(employeeDto);
        EmployeeEntity employeeEntity1=employeeRepository.save(employeeEntity);
        EmployeeDto employeeDto1=EmployeeMapper.mapEntitytoDto(employeeEntity1);
        return  employeeDto1;
    }

    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> {
            String name = employeeService.getDepartmentNameById(employeeEntity.getDepartmentId());
            employeeEntity.setDepartmentName(name);
            return EmployeeMapper.mapEntitytoDto(employeeEntity);
        }).collect(Collectors.toList());
    }

    public List<DepartmentDto> getAllDepartents(){
        List<DepartmentEntity> departmentEntities=departmentRepository.findAll();
        List<DepartmentDto> departmentDtos=departmentEntities.stream().map(departmentEntity ->
                DepartmentMapper.mapEntityToDto(departmentEntity)).collect(Collectors.toList());
        return  departmentDtos;
    }

    public List<EmployeeDto> getEmployeeByDepartmentId(Integer departmentId){
        List<EmployeeEntity> employeeEntities = employeeRepository.findByDepartmentId(departmentId);
        List<EmployeeDto> employeeDtos = employeeEntities.stream().map(employeeEntity ->
                EmployeeMapper.mapEntitytoDto(employeeEntity)).collect(Collectors.toList());
        return employeeDtos;
    }

    public  EmployeeDto getEmployeeByID(Integer id){
    Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
    if(employeeEntity.isPresent()){
        EmployeeEntity employeeEntity1=employeeEntity.get();
        return EmployeeMapper.mapEntitytoDto(employeeEntity1);
    }
    else {
        return  null;
    }
}

    @Transactional
    public EmployeeDto updateEmployeeById(Integer employeeId, EmployeeDto updatedEmployeeDto) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);

        if (optionalEmployeeEntity.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();

            employeeEntity.setName(updatedEmployeeDto.getName());
            employeeEntity.setEmail(updatedEmployeeDto.getEmail());
            employeeEntity.setSalary(updatedEmployeeDto.getSalary());
            employeeEntity.setPosition(updatedEmployeeDto.getPosition());
            employeeEntity.setDepartmentId(updatedEmployeeDto.getDepartmentId());

            employeeRepository.save(employeeEntity);

            return EmployeeMapper.mapEntitytoDto(employeeEntity);
        } else {

            return null;
        }
    }

    public String deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
           return  "Deleted successfully";
    }
}