package com.person_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
