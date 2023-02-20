package com.example.employee.service;

import com.example.employee.model.DepartmentVO;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeVO;
import com.example.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public EmployeeVO getEmployee(Long empId) {
        log.info("Fetching employee");
        var employee = employeeRepository.findById(empId).orElse(null);
        var response = restTemplate.getForEntity("http://localhost:9001/departments/" + employee.getDepartmentId(), DepartmentVO.class);

        DepartmentVO departmentVO = null;
        if(response.getStatusCode() == HttpStatus.OK) {
            departmentVO = response.getBody();
        }

        return new EmployeeVO(employee.getId(), employee.getName1(), departmentVO);
    }
}
