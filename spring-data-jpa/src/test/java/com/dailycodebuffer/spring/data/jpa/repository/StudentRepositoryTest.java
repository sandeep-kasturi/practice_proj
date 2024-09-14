package com.dailycodebuffer.spring.data.jpa.repository;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycodebuffer.spring.data.jpa.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.entity.Student;

@SpringBootTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent(){
		Student student = Student.builder().firstName("sandeep").lastName("kasturi").emailId("kasturisandeep24@gmail.com").build();

		studentRepository.save(student);
	}

	@Test
	public void printAllStudent(){
		List<Student> studentList = studentRepository.findAll();
		System.out.println("StudentList +" + studentList);
	}

	@Test
	public void saveStudentWithGuardian(){

		Guardian guardian = Guardian.builder().name("Eshwar").email("eshwar@gmail.com").mobile("9848012345").build();
		Student student = Student.builder().firstName("sand").lastName("kast").emailId("sandkast6@gmail.com").guardian(guardian).build();

		studentRepository.save(student);
	}

	@Test
	public void printStudentByFirstName(){
		List<Student> students = studentRepository.findByFirstName("sandeep");
		System.out.println("Students = "+students);
	}

	@Test
	public void printStudentByFirstNameContaining(){
		List<Student> students = studentRepository.findByFirstNameContaining("sa");
		System.out.println("Students = "+students);
	}

	@Test
	public void printStudentWhereLastNameNotNull(){
		List<Student> students = studentRepository.findByLastNameNotNull();
		System.out.println("Students ="+students);
	}

	@Test 
	public void printStudentBasedOnGuardianName(){
		List<Student> students = studentRepository.findByGuardianName("Eshwar");
		System.out.println("students = "+students);
	}

	@Test
	public void printStudentByEmailAddress(){
		List<Student> students = studentRepository.getStudentByEmailAddress("sandkast6@gmail.com");
		System.out.println("student ="+students);
	}

	@Test
	public void printFirstNameByEmail(){
		String student = studentRepository.getStudentFirstNameByEmailAddress("sandkast6@gmail.com");
		System.out.println("FirstName = "+student);
	}

	@Test
	public void printStudentFirstNameOrLastName(){
		List<Student> student = studentRepository.getStudentFirstNameOrLastName("sandeep","kast");
		System.out.println("Students ="+student);
	}

	@Test
	public void printStudentByEmailAddressNative(){
		List<Student> student = studentRepository.getStudentByEmailAddressNative("sandkast6@gmail.com");
		System.out.println("Student ="+student);
	}

	@Test
	public void printStudentByEmailAddressNativeNamedParam(){
		List<Student> student = studentRepository.getStudentByEmailAddressNativeNamedParam("sandkast6@gmail.com");
		System.out.println("Student ="+student);
	}

	@Test
	public void updateStudentNameByEmailId(){
		 studentRepository.updateStudentNameByEmailId("sand_update", "sandkast5@gmail.com");
	} 
}
