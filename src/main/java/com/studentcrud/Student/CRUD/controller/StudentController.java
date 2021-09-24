package com.studentcrud.Student.CRUD.controller;

import com.studentcrud.Student.CRUD.model.Student;
import com.studentcrud.Student.CRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/allStudent")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}
