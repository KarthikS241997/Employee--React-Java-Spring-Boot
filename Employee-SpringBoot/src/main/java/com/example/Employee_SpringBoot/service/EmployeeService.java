package com.example.Employee_SpringBoot.service;

import com.example.Employee_SpringBoot.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto empDto);

    EmployeeDto getEmployeeById(Long empId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long empId);
}
