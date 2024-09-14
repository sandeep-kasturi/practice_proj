package com.dailycodebuffer.Springboot.tutorial.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.Springboot.tutorial.service.DepartmentService;

import org.springframework.http.MediaType;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private Department department;
	
	@BeforeEach
	void setUp(){
		department = Department.builder().departmentAddress("HYD").departmentCode("IT-06").departmentName("IT").departmentId(1L).build();
	}

	@Test
	void SaveDepartment() throws Exception {
		Department inputDepartment = Department.builder().departmentAddress("HYD").departmentCode("IT-06").departmentName("IT").build();
		Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON).content("{\r\n"
				+ "    \"departmentName\":\"IT\",\r\n"
				+ "    \"departmentAddress\":\"HYD\",\r\n"
				+ "    \"departmentCode\":\"IT-06\"\r\n"
				+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void FetchDepartmentById() throws Exception{
		Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);
		
		mockMvc.perform(get("/departments/1").contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
	}

}
