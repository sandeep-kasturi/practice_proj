package com.dailycodebuffer.usr.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailycodebuffer.dep.entity.Department;

//@FeignClient(name="DEPARTMENT-SERVICE", url="http://localhost:9001/")
@FeignClient(name="DEPARTMENT-SERVICE")
public interface DepartmentFeign {
	@GetMapping("/departments/{id}")
	 public Department findDepartmentById(@PathVariable("id") Long departmentId);
}
