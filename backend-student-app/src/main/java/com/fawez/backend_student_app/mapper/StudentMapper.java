package com.fawez.backend_student_app.mapper;

import com.fawez.backend_student_app.dto.StudentDto;
import com.fawez.backend_student_app.entities.Student;

public class StudentMapper {
    public static Student toStudent(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }
        return Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .code(studentDto.getCode())
                .photo(studentDto.getPhoto())
                .programId(studentDto.getProgramId())
                .build();
    }
    public static StudentDto toStudentDto(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentDto(
                student.getId(),
                student.getCode(),
                student.getFirstName(),
                student.getLastName(),
                student.getProgramId(),
                student.getPhoto()
        );
    }
}
