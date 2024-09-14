package com.dailycodebuffer.usr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dailycodebuffer.usr.entity.User;
import com.dailycodebuffer.usr.service.UserService;
import com.dailycodebuffer.usr.vo.ResponseTemplateVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    
    
    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

//    @CircuitBreaker(name="breaker1", fallbackMethod = "fallbackForBreaker1")
    @Retry(name="breaker1", fallbackMethod = "fallbackForBreaker1")
//    @RateLimiter(name="breaker1")
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        
        return userService.getUserWithDepartment(userId);
    }
    
    public ResponseTemplateVO fallbackForBreaker1(Long userId, Throwable throwable) {
    	log.info("fallback for breker1");
    	return new ResponseTemplateVO();
    }


}