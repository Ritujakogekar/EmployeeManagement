package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeDto;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.mapper.EmployeeMapper;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.management.JMRuntimeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JasperReportService {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    public byte[] generateReport() throws JRException, FileNotFoundException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("Name","IVORY");

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos =  employeeEntities.stream().map(employeeEntity -> {
            String name = employeeService.getDepartmentNameById(employeeEntity.getDepartmentId());
            employeeEntity.setDepartmentName(name);
            return EmployeeMapper.mapEntitytoDto(employeeEntity);
        }).collect(Collectors.toList());


        File file = ResourceUtils.getFile("classpath:JasperFile.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeDtos);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Stark Solutions\\Documents\\employees_report.pdf");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
