package com.springboot.learnjpaandhibernate.course.jpa;

import com.springboot.learnjpaandhibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext //instead of Autowired
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public void delete(long id){
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

}
