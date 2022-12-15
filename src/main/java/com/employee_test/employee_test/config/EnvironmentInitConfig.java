package com.employee_test.employee_test.config;

import com.employee_test.employee_test.entity.Employee;
import com.employee_test.employee_test.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentInitConfig {

    @Bean
    CommandLineRunner putEmployeesToTheDatabase(EmployeeRepository employeeRepository) {
        if (employeeRepository.count() > 0) {
            return null;
        }
        return args -> {
            employeeRepository.save(Employee.builder()
                    .firstName("John")
                    .lastName("Tarasov")
                    .build());
            employeeRepository.save(Employee.builder()
                    .firstName("Oleksii")
                    .lastName("Borsykov")
                    .build());
            employeeRepository.save(Employee.builder()
                    .firstName("Oleh")
                    .lastName("Johnson")
                    .build());
            employeeRepository.save(Employee.builder()
                    .firstName("Mykola")
                    .lastName("Piterson")
                    .build());
            employeeRepository.save(Employee.builder()
                    .firstName("Mickle")
                    .lastName("Ivanov")
                    .build());
        };
    }
}
