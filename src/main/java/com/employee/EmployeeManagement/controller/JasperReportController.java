package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.service.JasperReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/jasper")
@AllArgsConstructor
public class JasperReportController {

    private JasperReportService jasperReportService;

    @CrossOrigin
    @GetMapping("/employees")
    public ResponseEntity<byte[]> generateEmployeeReport() {
        try {
            byte[] pdfBytes = jasperReportService.generateReport();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "employees_report.pdf");
            headers.setContentLength(pdfBytes.length);
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (JRException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
