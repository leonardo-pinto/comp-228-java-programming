package com.springboot.learnjpaandhibernate.course.jdbc;

import com.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository courseJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
//        courseJdbcRepository.insert(new Course(1, "Learn AWS", "John Doe"));
//        courseJdbcRepository.insert(new Course(2, "Learn Azure", "John Doe"));
//        courseJdbcRepository.insert(new Course(3, "Learn GCP", "John Doe"));
//
//        courseJdbcRepository.delete(3);
//
//        System.out.println(courseJdbcRepository.findById(1));

    }
}
