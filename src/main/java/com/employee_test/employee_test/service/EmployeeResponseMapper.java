package com.employee_test.employee_test.service;

import com.employee_test.employee_test.dto.EmployeeResponse;
import com.employee_test.employee_test.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeResponseMapper implements Mapper<EmployeeResponse, Employee> {

    @Override
    public EmployeeResponse map(Employee from) {
        return EmployeeResponse.builder()
                .id(from.getId())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .birthDate(from.getBirthDate())
                .build();
    }

    @Override
    public List<EmployeeResponse> map(List<Employee> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
