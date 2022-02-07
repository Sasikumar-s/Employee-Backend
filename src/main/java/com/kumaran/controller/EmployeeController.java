package com.kumaran.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumaran.exception.ResourceNotFoundException;
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
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> empById(@PathVariable Long id) {
		Employee employee= repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Found on this ID:"+id));
		return ResponseEntity.ok(employee);
		}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee emp){
		Employee employee= repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Found on this ID:"+id));
		employee.setEmpName(emp.getEmpName());
		employee.setEmpDept(emp.getEmpDept());
		employee.setEmpSalary(emp.getEmpSalary());
		Employee updateStatus = repo.save(employee);
		return ResponseEntity.ok(updateStatus);
	}
	@DeleteMapping("/employees{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee= repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Found on this ID:"+id));
		repo.delete(employee);
		Map<String,Boolean> response= new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
