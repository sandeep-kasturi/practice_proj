package com.dailycodebuffer.Springboot.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;

@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp(){
		Department department = Department.builder().departmentName("ME").departmentCode("ME-001").departmentAddress("Delhi").build();
		entityManager.persist(department);
	}

	@Test
	@DisplayName("Testing Repository layer")
	void test() {
		Department department = departmentRepository.findById(1L).get();
		assertEquals(department.getDepartmentName(),"ME");
	}

}
