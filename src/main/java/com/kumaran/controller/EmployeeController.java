package com.kumaran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumaran.model.Employee;
import com.kumaran.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/sk/")
public class EmployeeController {
	@Autowired
	EmployeeRepository repo;
	@GetMapping("/employees")
	public List<Employee> getAllEmp(){
		return repo.findAll();
	}
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee emp) {
		return repo.save(emp);
	}
}
