package com.fawez.backend_student_app.mapper;

import com.fawez.backend_student_app.dto.PaymentDto;
import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.Student;

public class PaymentMapper {
    public static Payment toPayment(PaymentDto paymentDto){
        return Payment.builder()
                .student(Student.builder()
                        .id(paymentDto.getStudentId())
                        .build())
                .file(paymentDto.getFile())
                .type(paymentDto.getType())
                .date(paymentDto.getDate())
                .amount(paymentDto.getAmount())
                .status(paymentDto.getStatus())
                .build();

    }
    public static PaymentDto toPaymentDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getType(),
                payment.getStatus(),
                payment.getFile(),
                payment.getStudent().getId()
        );
    }
}
