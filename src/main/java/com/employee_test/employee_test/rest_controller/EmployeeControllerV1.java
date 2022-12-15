package com.employee_test.employee_test.rest_controller;

import com.employee_test.employee_test.dto.EmployeeResponse;
import com.employee_test.employee_test.entity.Employee;
import com.employee_test.employee_test.service.EmployeeResponseMapper;
import com.employee_test.employee_test.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeControllerV1 {
    private final EmployeeService employeeService;
    private final EmployeeResponseMapper employeeResponseMapper;

    public EmployeeControllerV1(EmployeeService employeeService, EmployeeResponseMapper employeeResponseMapper) {
        this.employeeService = employeeService;
        this.employeeResponseMapper = employeeResponseMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        var employee = employeeService.getById(id);
        if (employee.isPresent()) {
            var employeeResponse = employeeResponseMapper.map(employee.get());
            return ResponseEntity.ok(employeeResponse);
        }
        return new ResponseEntity<>(String.format("Employee with id=%s not found", id), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee employee) {
        var createdEmployee =  employeeService.create(employee);
        var createdEmployeeResponse = employeeResponseMapper.map(createdEmployee);
        return new ResponseEntity<>(createdEmployeeResponse, HttpStatus.CREATED);
    }
}
