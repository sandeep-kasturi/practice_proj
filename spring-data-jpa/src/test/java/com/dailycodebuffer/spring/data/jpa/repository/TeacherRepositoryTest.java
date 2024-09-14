package com.dailycodebuffer.spring.data.jpa.repository;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycodebuffer.spring.data.jpa.entity.Course;
import com.dailycodebuffer.spring.data.jpa.entity.Teacher;


@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    // @Test
    // public  void saveTeacher(){
    //     Course courseDSA = Course.builder().title("DSA").credit(5).build();
    //     Course coursePython = Course.builder().title("Python").credit(4).build();
    //     Teacher teacher = Teacher.builder().firstName("ramana").lastName("pokala").course(List.of(courseDSA, coursePython)).build();

    //     teacherRepository.save(teacher);

    // }
}
