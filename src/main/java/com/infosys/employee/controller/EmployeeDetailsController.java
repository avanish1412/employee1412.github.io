package com.infosys.employee.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.infosys.employee.dao.Employee;
import com.infosys.employee.repository.EmployeeDetailsRepository;
import com.infosys.employee.service.EmployeeDetailsService;
import com.infosys.employee.service.KafkaConsumer;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private KafkaConsumer kafkaConsumer;


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
    public void removeEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDetailsRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employeeDetailsRepository.deleteById(employee.getId());
        }
    }

    @Scheduled(fixedRate = 100000)
    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void listen(ConsumerRecord<String, Employee> record) {
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Partition: " + record.partition());
    }

}
