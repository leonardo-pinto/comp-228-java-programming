package com.springboot.learnjpaandhibernate.course.jpa;

import com.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class CourseJpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public void run(String... args) throws Exception {
//        courseJpaRepository.insert(new Course(1, "Learn React", "John Doe"));
//        courseJpaRepository.insert(new Course(2, "Learn Angular", "John Doe"));
//        courseJpaRepository.insert(new Course(3, "Learn Vue", "John Doe"));
//
//        courseJpaRepository.delete(3);
//
//        System.out.println(courseJpaRepository.findById(1));

    }
}
