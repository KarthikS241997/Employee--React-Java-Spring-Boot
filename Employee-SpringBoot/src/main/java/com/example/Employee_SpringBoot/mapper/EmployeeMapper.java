package com.example.Employee_SpringBoot.mapper;

import com.example.Employee_SpringBoot.dto.EmployeeDto;
import com.example.Employee_SpringBoot.model.Employee;

public class EmployeeMapper {

    // Employee entity to employee DTO
    public static EmployeeDto mapToEmployeeDto(Employee emp) {
        return new EmployeeDto(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail()
        );
    }

    // Employee DTO to employee Jpa Entity
    public static Employee mapToEmployee(EmployeeDto empDto) {
        return new Employee(
                empDto.getId(),
                empDto.getFirstName(),
                empDto.getLastName(),
                empDto.getEmail()
        );
    }
}
