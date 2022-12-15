package com.employee_test.employee_test.service;

import com.employee_test.employee_test.entity.Employee;

import java.util.Optional;

public interface EmployeeService {
    Employee create(Employee employee);
    Optional<Employee> getById(Long id);
}
