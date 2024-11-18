package com.fawez.backend_student_app.dto;

import com.fawez.backend_student_app.entities.PaymentStatus;
import com.fawez.backend_student_app.entities.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {

    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file;
    private String studentId;
}
