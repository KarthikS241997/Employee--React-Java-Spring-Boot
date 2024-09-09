package com.example.Employee_SpringBoot.controller;

import com.example.Employee_SpringBoot.dto.EmployeeDto;
import com.example.Employee_SpringBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto) {
        return new ResponseEntity<>(empService.createEmployee(empDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {  //if the URl template variable and parameter variable names are different specify in path variable
        return new ResponseEntity<>(empService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee) {
        return ResponseEntity.ok(empService.updateEmployee(employeeId, updatedEmployee));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        empService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted");
    }
}
