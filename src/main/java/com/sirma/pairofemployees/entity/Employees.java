package com.sirma.pairofemployees.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "employees")
@Entity
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;

}