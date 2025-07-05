package com.infosys.employee.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.infosys.employee.dao.Employee;
import com.infosys.employee.repository.EmployeeDetailsRepository;
import com.infosys.employee.service.EmployeeDetailsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;


    @GetMapping("/employee/get")
    public List<Employee> getIndex(Model model) {
        return employeeDetailsRepository.findAll();
    }

    @GetMapping("/employee/get/{id}")
    public Employee getIndex(@PathVariable("id") String id) {
        return employeeDetailsService.getIndex(id);
    }

    // Insert employee data
    @Transactional
    @PostMapping("/employee/create")
    public void newEmployee(Employee employee) {
        // save the employee
        employeeDetailsRepository.save(employee);

    }

    // update the existing employee
    @Transactional
    @PostMapping("/employee/update")
    public void updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDetailsRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDetailsRepository.save(employee);
        }
    }

    // delete an employee by id
    @Transactional
    @PostMapping("/employee/remove")
    public void removeEmployee(Employee employee, Model model) {
        model.addAttribute("employee", new Employee());
        Optional<Employee> existingEmployee = employeeDetailsRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDetailsRepository.deleteById(employee.getId());
        }
    }
}
