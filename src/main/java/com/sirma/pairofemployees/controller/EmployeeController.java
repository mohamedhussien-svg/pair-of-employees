package com.sirma.pairofemployees.controller;

import com.sirma.pairofemployees.entity.Employees;
import com.sirma.pairofemployees.projection.EmployeeProjection;
import com.sirma.pairofemployees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("/pair-of-employees")
    public ResponseEntity<List<EmployeeProjection>> uploadEmployeeData(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return new ResponseEntity<>(employeeService.pairEmployeesProcessing(multipartFile), HttpStatus.OK);
    }
}
