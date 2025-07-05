package com.infosys.employee.dao;

import jakarta.persistence.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE")
@RedisHash("Employee")
public class Employee implements Serializable {
    @Id
    private String id;
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;
    @Column(name = "EMPLOYEE_PHONE")
    private Long employeePhone;
    @Column(name = "EMPLOYEE_GENDER")
    private String employeeGender;
    @Column(name = "EMPLOYEE_SALARY")
    private String employeeSalary;
    @Column(name = "EMPLOYEE_ROLE")
    private String employeeRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(Long employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
