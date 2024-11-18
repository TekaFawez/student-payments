package com.fawez.backend_student_app.repository;

import com.fawez.backend_student_app.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
  Student findByCode(String code);
}
