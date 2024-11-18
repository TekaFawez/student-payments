package com.fawez.backend_student_app.web;

import com.fawez.backend_student_app.dto.PaymentDto;
import com.fawez.backend_student_app.dto.StudentDto;
import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.PaymentType;
import com.fawez.backend_student_app.mapper.PaymentMapper;
import com.fawez.backend_student_app.repository.PaymentRepository;
import com.fawez.backend_student_app.repository.StudentRepository;
import com.fawez.backend_student_app.service.PaymentService;
import com.fawez.backend_student_app.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin("*")
public class StudentRestController {

public final StudentService studentService;
public final PaymentService paymentService;

    public StudentRestController(StudentService studentService, PaymentService paymentService) {
        this.studentService = studentService;
        this.paymentService = paymentService;
    }
    @GetMapping(path = "/students")
    public List<StudentDto> allStudents(){
        return studentService.findAllStudents();
    }
    @GetMapping(path = "/students/{code}")
    public StudentDto studentByCode(@PathVariable("code") String code){
        return studentService.findStudentsByCode(code);
    }
    @GetMapping("/payments")

    public List<PaymentDto> allPayment(){
       return studentService.findAllPayments();
    }

    @PostMapping(path="/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PaymentDto payment(@Parameter(description = "The payment file") @RequestParam MultipartFile file, double amount, PaymentType type,
                              LocalDate date, String studentCode) throws IOException {
       return paymentService.savePayment(file,amount,studentCode,type,date);

    }
    @GetMapping(path="payments/{id}/file",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return paymentService.getPaymentFile(id);

    }
}