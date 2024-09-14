package com.dailycodebuffer.spring.data.jpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.dailycodebuffer.spring.data.jpa.entity.Course;
import com.dailycodebuffer.spring.data.jpa.entity.Student;
import com.dailycodebuffer.spring.data.jpa.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest {

		@Autowired
		private CourseRepository courseRepository;

		@Test
		public void printCourses(){
			List<Course> course = courseRepository.findAll();
			System.out.println("courses ="+course);
		}
	
		@Test
		public void saveCourseWithTeacher(){

			Teacher teacher = Teacher.builder().firstName("priyanka").lastName("singh").build();

			Course course = Course.builder().title("java").credit(6).teacher(teacher).build();
			courseRepository.save(course);
		} 

		@Test
		public void findAllPagination(){
			Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
			Pageable SecondPageWithTwoRecords = PageRequest.of(1,2);

			List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

			Long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

			int totalPages = courseRepository.findAll(SecondPageWithTwoRecords).getTotalPages();

			System.out.println("courses ="+courses);
			System.out.println("totalPages ="+totalPages);
			System.out.println("totalElements ="+totalElements);
		}

		@Test
		public void findAllSorting(){
			Pageable sortByTitle = PageRequest.of(0,8,Sort.by("title"));
			Pageable sortByCreditDesc = PageRequest.of(0,8,Sort.by("credit").descending());
			Pageable sortByTitleAndCreditDesc = PageRequest.of(0,8,Sort.by("title").descending().and(Sort.by("credit")));

			List<Course> courses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
			System.out.println("courses ="+ courses);
		}

		@Test
		public void printfindByTitleContaining(){
			Pageable firstPageTenRecords = PageRequest.of(0,10);
			List<Course> courses = courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();

			System.out.println("coursess ="+ courses);
		}

		@Test
		public void saveCourseWithStudentAndTeacher(){

			Teacher teacher = Teacher.builder().firstName("Shan").lastName("Ruseph").build();
			Course course = Course.builder().title("AIML").credit(2).teacher(teacher).build();

			Student student = Student.builder().firstName("abhith").lastName("singh").emailId("abhith@gmal.com").build();
			course.addStudents(student);
			courseRepository.save(course);
		}
}
 