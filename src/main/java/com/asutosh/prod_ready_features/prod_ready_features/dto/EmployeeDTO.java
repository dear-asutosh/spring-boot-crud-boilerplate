package com.asutosh.prod_ready_features.prod_ready_features.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private int age;

    private String role;

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean active;
}
