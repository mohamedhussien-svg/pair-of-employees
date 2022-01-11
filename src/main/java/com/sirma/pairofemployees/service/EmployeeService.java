package com.sirma.pairofemployees.service;

import com.sirma.pairofemployees.entity.Employees;
import com.sirma.pairofemployees.projection.EmployeeProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<Employees> getAllEmployees();

    List<EmployeeProjection> pairEmployeesProcessing(MultipartFile multipartFile) throws  Exception;
}
