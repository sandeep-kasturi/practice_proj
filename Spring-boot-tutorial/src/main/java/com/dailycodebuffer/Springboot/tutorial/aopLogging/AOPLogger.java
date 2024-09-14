package com.dailycodebuffer.Springboot.tutorial.aopLogging;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class AOPLogger {

	@Before(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		log.info("@Before - Requested to " + joinpoint.getSignature() + "stated at " + new Date());
	}
	
	@After(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.*(..))")
	public void afterAdvice(JoinPoint joinpoint) {
		log.info("@After - Requested to " + joinpoint.getSignature() + "ended at " + new Date());
	}
	
	@AfterReturning(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.fetchDepatmentList(..))")
	public void afterReturningWithoutValues(JoinPoint joinPoint) {
		log.info("@AfterReturning - Business logic execution : " + joinPoint.getSignature() + "returned at " + new Date());
	}
	
	@AfterReturning(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.fetchDepatmentList(..))", returning = "department")
	public void afterReturningWithValues(JoinPoint joinPoint, List<Department> department) {
		log.info("@AfterReturning - Business logic execution : " + joinPoint.getSignature() + "returned at " + new Date() + "and the list length of " + department.size() );
	}
	
	@AfterThrowing(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.*(..))", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		log.info("@AfterThrowing - Business logic execution : " + joinPoint.getSignature() + "failed at " + new Date() + "with message : " + exception.getMessage());
	}
	
//	@Around(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.fetchDepatmentList(..))")
//	public void around(ProceedingJoinPoint joinPoint) {
//		log.info("Inside @Around of Aspect : " + joinPoint.getSignature() + "started at " + new Date());
//		try {
//			joinPoint.proceed();
//		} catch (Throwable e) {
//			log.info("Inside @Around of Aspect catch box : " + joinPoint.getSignature() + "failed with message : " + e.getMessage());
//		}
//	}
	
	@Around(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.fetchDepatmentList(..))")
	public List<Department> around(ProceedingJoinPoint joinPoint) {
		log.info("Inside @Around of Aspect : " + joinPoint.getSignature() + "started at " + new Date());
		try {
			List<Department> departments = (List<Department>) joinPoint.proceed();
			return departments;
		} catch (Throwable e) {
			log.info("Inside @Around of Aspect : " + joinPoint.getSignature() + "failed with message : " + e.getMessage());
			return null;
		}
	}
	
	@Around(value = "execution(* com.dailycodebuffer.Springboot.tutorial.controller.DepartmentController.saveDepartment(..))")
	public ResponseEntity<Object> aroundForSaveDepartment(ProceedingJoinPoint joinPoint) {
		log.info("Inside @Around of Aspect : " + joinPoint.getSignature() + "started at " + new Date());
		try {
			Department[] arr = new Department[1];
			Department obj = new Department();
			obj.setDepartmentAddress("abc");
			obj.setDepartmentCode("abc");
			obj.setDepartmentName("abc");
			arr[0] = obj;
			
			ResponseEntity<Object> res = (ResponseEntity<Object>) joinPoint.proceed(arr);
			return res;
		} catch (Throwable e) {
			log.info("Inside @Around of Aspect : " + joinPoint.getSignature() + "failed with message : " + e.getMessage());
			return null;
		}
	}
	
	
}
