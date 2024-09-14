package com.dailycodebuffer.Springboot.tutorial.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.Springboot.tutorial.exception.NotEmptyException;
import com.dailycodebuffer.Springboot.tutorial.service.DepartmentService;

import jakarta.validation.Valid;


@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
		
//	@PostMapping("/deparments")
		@RequestMapping(value = "/departments", produces = "application/json", method=RequestMethod.POST)
		public ResponseEntity<Object> saveDepartment(@RequestBody Department department) throws NotEmptyException{
			LOGGER.info("Inside saveDepartment of DepartmentController");
//			try {
				return new ResponseEntity<Object>(departmentService.saveDepartment(department),HttpStatus.OK) ;
//			} catch (NotEmptyException e) {
//				
//				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST) ; 
//			}
		}
		

	
	@GetMapping("/departments")
	public List<Department> fetchDepatmentList(){
//		LOGGER.info("Inside fetchDepatmentList of DepartmentController");
//		throw new RuntimeException("intentionally provided exception");
		return departmentService.fetchDepartmentList();
	}
	
	@GetMapping("/departments/{id}")
	public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentById(departmentId);
	}
	
	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Department deleted Successfully";
	}
	
	@PutMapping("/departments/{id}")
	public Department updateDepartment(@PathVariable Long departmentId, @RequestBody Department department) {
		return departmentService.updateDepartment(departmentId, department);
	}
	
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
	
	
}
