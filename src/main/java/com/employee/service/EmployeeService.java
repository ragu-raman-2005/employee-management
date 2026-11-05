
package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Add Employee
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get Employee By ID
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Employee not found"));

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
