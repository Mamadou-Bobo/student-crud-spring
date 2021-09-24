package com.studentcrud.Student.CRUD.repository;

import com.studentcrud.Student.CRUD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //Student findByUserName(String username);
}
