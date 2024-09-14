package com.dailycodebuffer.usr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycodebuffer.usr.controller.DepartmentFeign;
import com.dailycodebuffer.usr.entity.User;
import com.dailycodebuffer.usr.repository.UserRepository;
import com.dailycodebuffer.dep.entity.Department;
import com.dailycodebuffer.usr.vo.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentFeign departmentFeign;


    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId){
        log.info("Inside getUserWithDepartment of UserService");
        
        if(userId == 1) {
        	throw new RuntimeException();
        }
        
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

//        Department department =
//                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
//                        ,Department.class);
        Department department = departmentFeign.findDepartmentById(user.getDepartmentId());
		vo.setUser(user);
		vo.setDepartment(department);

        return  vo;
    }
}