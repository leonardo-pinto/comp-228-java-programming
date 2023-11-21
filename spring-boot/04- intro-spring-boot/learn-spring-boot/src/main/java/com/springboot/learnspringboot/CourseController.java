package com.springboot.learnspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
//        List<Course> courses = new ArrayList<Course>();
//        courses.add(new Course(1, "Learn AWS"));
//        courses.add(new Course(2, "Learn Go"));

        return Arrays.asList(
                new Course(1, "Learn AWS"),
                new Course(2, "Learn Go"),
                new Course(3, "Learn Google Cloud Platform"),
                new Course(4, "Learn Laravel"));
    }
}
