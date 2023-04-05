package com.saparov.springbootproject.controller;

import com.saparov.springbootproject.model.Employee;
import com.saparov.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }
}
