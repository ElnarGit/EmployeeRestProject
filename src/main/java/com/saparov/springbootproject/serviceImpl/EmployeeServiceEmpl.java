package com.saparov.springbootproject.serviceImpl;

import com.saparov.springbootproject.exception.ResourceNotFoundException;
import com.saparov.springbootproject.model.Employee;
import com.saparov.springbootproject.repository.EmployeeRepository;
import com.saparov.springbootproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceEmpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceEmpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
       /*
        Optional<Employee> foundEmployee =  employeeRepository.findById(id);
        if(foundEmployee.isPresent()){
            return foundEmployee.get();
        }else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }*/

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmail(employee.getEmail());

         employeeRepository.save(updateEmployee);
         return updateEmployee;
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

       employeeRepository.deleteById(id);
    }
}
