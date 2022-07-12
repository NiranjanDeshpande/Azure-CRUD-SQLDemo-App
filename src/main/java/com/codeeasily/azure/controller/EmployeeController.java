package com.codeeasily.azure.controller;

import com.codeeasily.azure.entity.Employee;
import com.codeeasily.azure.exception.ResourceNotFoundException;
import com.codeeasily.azure.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/wmsg")
    public String welcomeController(){
        return " Welcome to REST CRUD Azure API ! ";
    }

    @PostMapping(value = "/createEmp", produces = "application/json")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping(value = "/getEmp")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    
    @PutMapping(value = "/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,
                                   @PathVariable (value = "id") int id){
        Employee existingEmp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" Employee Not found with id : " + id));
        existingEmp.setName(employee.getName());
        existingEmp.setDept(employee.getDept());
        existingEmp.setSalary(employee.getSalary());
        Employee updatedEmp = employeeRepository.save(existingEmp);
        return updatedEmp;
    }
}
