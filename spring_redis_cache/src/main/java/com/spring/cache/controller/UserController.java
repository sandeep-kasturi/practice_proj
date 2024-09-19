package com.spring.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cache.entity.User;
import com.spring.cache.service.UserService;

import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@EnableCaching
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody User user){
		try {
			log.info("starting of add mehtod");
			userService.add(user);
			return new ResponseEntity<Object>("Successfully added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	@Cacheable(value = "users", key = "#root.method.name", condition = "#result != null") // Cache only non-empty results
	public ResponseEntity<Object> getAll(){
		try {
			log.info("starting of getAll mehtod");
			List<User> users = userService.getAll();
			return new ResponseEntity<Object>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getById/{id}")
	@Cacheable(value = "users", key = "#id", condition = "#result != null")  // Cache by user ID
	public ResponseEntity<Object> getById(@PathVariable Long id){
		try {
			log.info("starting of getById mehtod");
			User user = userService.getById(id);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	@Caching(evict = {
	        @CacheEvict(value = "users", allEntries = true),  // Evict all entries from "users" cache
	        @CacheEvict(value = "userById", key = "#id")  // Evict specific user by ID cache (if configured)
	    })
	public ResponseEntity<Object> deleteById(@PathVariable Long id){
		try {
			log.info("starting of deleteById mehtod");
			userService.deleteById(id);
			return new ResponseEntity<Object>("successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateById/{id}")
	@Caching(
	        put = @CachePut(value = "users", key = "#id", condition = "#result != null"),  // Update cache if successful update
	        evict = @CacheEvict(value = "userById", key = "#id")  // Evict specific user by ID cache (if configured)
	    )
	public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody User user){
		try {
			log.info("starting of updateById mehtod");
			userService.updateById(id,user);
			return new ResponseEntity<Object>("successfully updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
