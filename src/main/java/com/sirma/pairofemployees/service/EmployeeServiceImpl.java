package com.sirma.pairofemployees.service;

import com.sirma.pairofemployees.entity.Employees;
import com.sirma.pairofemployees.projection.EmployeeProjection;
import com.sirma.pairofemployees.repositories.EmployeeRepo;
import com.sirma.pairofemployees.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public List<EmployeeProjection> pairEmployeesProcessing(MultipartFile multipartFile) throws Exception {
        uploadData(multipartFile);
        List<EmployeeProjection> pairsOfEmployees = getPairsOfEmployees();
        printPairsOfEmployees(pairsOfEmployees);
        return pairsOfEmployees;
    }


    private void uploadData(MultipartFile multipartFile) throws Exception {
        employeeRepo.deleteAll();

        Reader streamReader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cells = line.split("\t");
                Employees employees = new Employees();
                employees.setEmpId(Utils.getLongValue(cells, 0));
                employees.setProjectId(Utils.getLongValue(cells, 1));
                employees.setDateFrom(Utils.parseDate(cells[2]));
                employees.setDateTo(cells.length < 4  ? new Date() : Utils.parseDate(cells[3]));
                employeeRepo.save(employees);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Wrong Data on File");
        } finally {
            streamReader.close();
            bufferedReader.close();
        }

    }


    private List<EmployeeProjection> getPairsOfEmployees() {
        List<EmployeeProjection> pairEmployees = employeeRepo.getPairEmployees();
        Map<String, List<EmployeeProjection>> grouping = pairEmployees.stream().collect(Collectors.groupingBy(this::getKey));
        return grouping.keySet().stream().map(x -> grouping.get(x).stream().findAny().orElse(null)).collect(Collectors.toList());
    }

    private String getKey(EmployeeProjection x) {
        List<String> list =new ArrayList<>();
        list.add(x.getEmpId1().toString());
        list.add(x.getEmpId2().toString());
        Collections.sort(list);
        return String.join(":", list);
    }

    private void printPairsOfEmployees(List<EmployeeProjection> pairsOfEmployees) {
        pairsOfEmployees.forEach(x ->
                log.info("EmpId1 : " + x.getEmpId1() +
                        " EmpId2 : " + x.getEmpId2() +
                        " Project Id : " + x.getProjectId() +
                        " Working Days :" + x.getWorkingDays())
        );
    }


}
