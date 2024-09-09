package com.example.Employee_SpringBoot.service.implementation;

import com.example.Employee_SpringBoot.Exception.ResourceNotFoundException;
import com.example.Employee_SpringBoot.dto.EmployeeDto;
import com.example.Employee_SpringBoot.mapper.EmployeeMapper;
import com.example.Employee_SpringBoot.model.Employee;
import com.example.Employee_SpringBoot.repo.EmployeeRepo;
import com.example.Employee_SpringBoot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepo empRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto empDto) {

        Employee emp = EmployeeMapper.mapToEmployee(empDto);
        Employee savedEmp = empRepo.save(emp);
        return EmployeeMapper.mapToEmployeeDto(savedEmp);
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee = empRepo.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id " + empId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = empRepo.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto) //method referencing
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee) {
        Employee employee = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id "+ empId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObject =empRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObject);
    }

    @Override
    public void deleteEmployee(Long empId) {
        Employee employee = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id "+ empId));
        empRepo.deleteById(empId);
    }
}
