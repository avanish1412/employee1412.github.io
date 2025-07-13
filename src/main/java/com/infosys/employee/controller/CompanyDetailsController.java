package com.infosys.employee.controller;

import java.util.List;
import java.util.Optional;

import com.infosys.employee.dao.Employee;
import com.infosys.employee.repository.EmployeeDetailsRepository;
import com.infosys.employee.service.EmployeeDetailsService;
import com.infosys.employee.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class CompanyDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private KafkaProducer kafkaProducer;


    @GetMapping("/employee/get")
    @Scheduled(fixedRate = 100000)
    public List<Employee> getIndex() {
        kafkaProducer.sendMessage(employeeDetailsRepository.findAll().getFirst().getEmployeeName());
        return employeeDetailsRepository.findAll();
    }

    @GetMapping("/employee/get/{id}")
    public Employee getIndex(@PathVariable("id") String id) {
        return employeeDetailsService.getIndex(id);
    }

    // Insert employee data
    @PostMapping("/employee/create")
    public void newEmployee(Employee employee) {
        // save the employee
        employeeDetailsRepository.save(employee);

    }

    // update the existing employee
    public void updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDetailsRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDetailsRepository.save(employee);
        }
    }

    // delete an employee by id
    @PostMapping("/employee/remove")
    public void removeEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDetailsRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDetailsRepository.deleteById(employee.getId());
        }
    }
}
