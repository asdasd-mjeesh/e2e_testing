package com.employee_test.employee_test.service;

import com.employee_test.employee_test.entity.Employee;
import com.employee_test.employee_test.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDatabaseService implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDatabaseService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }
}
