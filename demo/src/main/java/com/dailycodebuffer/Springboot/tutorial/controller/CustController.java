package com.dailycodebuffer.Springboot.tutorial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.Springboot.tutorial.entity.Customer;

@RestController
@RequestMapping("/v1")
public class CustController {

	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Customer customer){
		// adding logic
		return new ResponseEntity<Object>(customer, HttpStatus.OK);
	}
}
