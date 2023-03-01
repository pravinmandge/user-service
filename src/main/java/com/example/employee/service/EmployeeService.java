package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeService {

    private final RestTemplate restTemplate;
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long empId) {
        log.info("Fetching employee");
        return employeeRepository.findById(empId).orElse(null);
    }

    public Iterable<Employee> getEmployees() {
        log.info("Fetching employees");
        return employeeRepository.findAll();
    }
}
