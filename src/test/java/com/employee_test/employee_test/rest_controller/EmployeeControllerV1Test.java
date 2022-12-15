package com.employee_test.employee_test.rest_controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import com.employee_test.employee_test.EmployeeTestApplication;
import com.employee_test.employee_test.entity.Employee;
import com.employee_test.employee_test.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = EmployeeTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-tests.properties")
@Transactional
class EmployeeControllerV1Test {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void getById() throws Exception {
        Employee employee = Employee.builder()
                .firstName("TestEmployee name")
                .lastName("TestEmployee surname")
                .birthDate(LocalDate.of(1337, 10, 11))
                .build();
        employee = createTestEmployee(employee);
        String birthDate = employee.getBirthDate().toString();

        mvc.perform(get("/api/v1/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("lastName", is(employee.getLastName())))
                .andExpect(jsonPath("birthDate", is(birthDate)));
    }

    private Employee createTestEmployee(Employee testEmployee) {
        var employee = Employee.builder()
                .firstName(testEmployee.getFirstName())
                .lastName(testEmployee.getLastName())
                .birthDate(testEmployee.getBirthDate())
                .build();
        return employeeRepository.save(employee);
    }
}