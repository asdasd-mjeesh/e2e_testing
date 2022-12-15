package com.employee_test.employee_test.repository;

import com.employee_test.employee_test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
