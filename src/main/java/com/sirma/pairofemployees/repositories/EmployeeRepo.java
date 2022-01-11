package com.sirma.pairofemployees.repositories;

import com.sirma.pairofemployees.entity.Employees;
import com.sirma.pairofemployees.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {

    @Query(value = "SELECT EMPLOYEE1 AS EMPID1, EMPLOYEE2 AS EMPID2, PROJECT_ID AS PROJECTID, EMP1_PERIOD AS WORKINGDAYS\n" +
            "FROM (SELECT E1.EMP_ID EMPLOYEE1,\n" +
            "             E2.EMP_ID EMPLOYEE2,\n" +
            "             E1.PROJECT_ID,\n" +
            "             (case\n" +
            "                  when DATEDIFF('DAY',E1.DATE_FROM, E1.DATE_TO) < DATEDIFF('DAY',E2.DATE_FROM, E2.DATE_TO)\n" +
            "                      then DATEDIFF('DAY',E1.DATE_FROM, E1.DATE_TO)\n" +
            "                  else DATEDIFF('DAY',E2.DATE_FROM, E2.DATE_TO)\n" +
            "                 end)  EMP1_PERIOD\n" +
            "\n" +
            "\n" +
            "      FROM employees E1,\n" +
            "           employees E2\n" +
            "      WHERE E1.DATE_TO >= E2.DATE_FROM\n" +
            "        AND E1.DATE_FROM <= E2.DATE_TO\n" +
            "        AND E1.PROJECT_ID = E2.PROJECT_ID\n" +
            "     ) D\n" +
            "WHERE D.EMPLOYEE1 <> D.EMPLOYEE2", nativeQuery = true)
    List<EmployeeProjection> getPairEmployees();
}
