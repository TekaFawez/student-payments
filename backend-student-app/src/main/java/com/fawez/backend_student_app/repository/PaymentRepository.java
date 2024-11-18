package com.fawez.backend_student_app.repository;

import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
}
