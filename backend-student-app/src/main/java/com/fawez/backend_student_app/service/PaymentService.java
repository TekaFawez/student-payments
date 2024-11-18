package com.fawez.backend_student_app.service;

import com.fawez.backend_student_app.dto.PaymentDto;
import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.PaymentStatus;
import com.fawez.backend_student_app.entities.PaymentType;
import com.fawez.backend_student_app.entities.Student;
import com.fawez.backend_student_app.mapper.PaymentMapper;
import com.fawez.backend_student_app.repository.PaymentRepository;
import com.fawez.backend_student_app.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaymentService {
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public byte[] getPaymentFile(Long id) throws IOException {
        Payment payment = paymentRepository.findById(id).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
    public PaymentDto savePayment(MultipartFile file,
                                  double amount ,
                                  String studentCode, PaymentType type,
                                  LocalDate date) throws IOException {
        Path folderPath= Paths.get(System.getProperty("user.home"),"ecole-students","payments");

        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName= UUID.randomUUID().toString();
        Path filePath=Paths.get(System.getProperty("user.home"),"ecole-students","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);

        Student student=studentRepository.findByCode(studentCode);
        Payment payment=Payment.builder()
                .type(type)
                .student(student)
                .amount(amount)
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .date(date)
                .build();
        Payment save= paymentRepository.save(payment);
        return PaymentMapper.toPaymentDto(save);

    }
}
