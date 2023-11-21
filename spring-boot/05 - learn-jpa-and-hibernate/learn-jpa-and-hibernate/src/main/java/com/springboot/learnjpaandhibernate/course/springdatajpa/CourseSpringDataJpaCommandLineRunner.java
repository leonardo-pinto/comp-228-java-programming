package com.springboot.learnjpaandhibernate.course.springdatajpa;

import com.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseSpringDataJpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Executed RUN");
        repository.save(new Course(1, "Learn Express", "John Doe"));
        repository.save(new Course(2, "Learn .NET", "Jane Doe"));
        repository.save(new Course(3, "Learn Spring Boot", "John Doe"));
        repository.deleteById(3L);

//        System.out.println(repository.findById(1L));
//        System.out.println(repository.findAll());
        System.out.println(repository.findByAuthor("Jane Doe"));
    }
}
