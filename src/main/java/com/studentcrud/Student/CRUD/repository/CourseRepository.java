package com.studentcrud.Student.CRUD.repository;

import com.studentcrud.Student.CRUD.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
   //Course findByCourseName(String courseName);
}
