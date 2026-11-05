package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Employee not found with ID: " + id
                    ));
    }

    // Save employee
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = getEmployeeById(id);

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    // Delete employee
    public void deleteEmployee(Long id) {

        repository.deleteById(id);
    }
}