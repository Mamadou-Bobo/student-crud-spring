package com.studentcrud.Student.CRUD.controller;

import com.studentcrud.Student.CRUD.exception.RessourceNotFoundException;
import com.studentcrud.Student.CRUD.model.Course;
import com.studentcrud.Student.CRUD.model.Student;
import com.studentcrud.Student.CRUD.repository.CourseRepository;
import com.studentcrud.Student.CRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional()
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/allCourses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Map<String, Boolean>> enrollStudentToCourse(@PathVariable Long courseId,
                                                     @PathVariable Long studentId){

        Map<String, Boolean> response = new HashMap<>();

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new RessourceNotFoundException("Course with id " + courseId + " does not exists"));
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RessourceNotFoundException("Student with id " + studentId + " does not exists"));

        if(student.getCourses().contains(course)){
            response.put(student.getFirstName() + " est déjà inscrit au cours de " + course.getName(), false);
            return ResponseEntity.ok(response);
        }

        course.enrollStudent(student);

        courseRepository.save(course);

        response.put(student.getFirstName() + " a été ajouté avec succès", true);

        return ResponseEntity.ok(response);
    }

}
