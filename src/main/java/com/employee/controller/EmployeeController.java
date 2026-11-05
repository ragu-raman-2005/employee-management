package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Display all employees
    @GetMapping
    public String listEmployees(Model model) {

        model.addAttribute(
            "employees",
            service.getAllEmployees()
        );

        return "employees";
    }

    // Show add employee form
    @GetMapping("/new")
    public String createEmployeeForm(Model model) {

        model.addAttribute("employee", new Employee());

        return "employee-form";
    }

    // Save employee
    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute Employee employee) {

        service.saveEmployee(employee);

        return "redirect:/employees";
    }

    // Edit employee
    @GetMapping("/edit/{id}")
    public String editEmployee(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
            "employee",
            service.getEmployeeById(id)
        );

        return "employee-form";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        service.deleteEmployee(id);

        return "redirect:/employees";
    }
}