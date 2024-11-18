package com.fawez.backend_student_app.service;

import com.fawez.backend_student_app.dto.PaymentDto;
import com.fawez.backend_student_app.dto.StudentDto;
import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.Student;
import com.fawez.backend_student_app.mapper.PaymentMapper;
import com.fawez.backend_student_app.mapper.StudentMapper;
import com.fawez.backend_student_app.repository.PaymentRepository;
import com.fawez.backend_student_app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;

    public StudentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDto> findAllPayments(){
        List<Payment> payments=paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }
    public List<StudentDto> findAllStudents(){
        List<Student> students=studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList());
    }
    public StudentDto findStudentsByCode(String code){
        Student student=studentRepository.findByCode(code);
        return StudentMapper.toStudentDto(student);

    }

}
