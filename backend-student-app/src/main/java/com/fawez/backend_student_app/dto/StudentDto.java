package com.fawez.backend_student_app.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    private String id;
    private String code;
    private String firstName;
    private String lastName;
    private String programId;
    private String photo;
}
