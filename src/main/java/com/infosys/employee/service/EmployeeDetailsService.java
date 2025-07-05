package com.infosys.employee.service;

import com.infosys.employee.dao.Employee;
import com.infosys.employee.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Cacheable(value = "employeeCache", key = "#id")
    public Employee getIndex(String id) {
        Optional<Employee> employeeOptional = employeeDetailsRepository.findById(id);
        Object cached = redisTemplate.opsForValue().get(id);
        System.out.println(cached != null ? "Cache hit!" : "Cache miss!");
        return employeeOptional.orElse(null);
    }
}
