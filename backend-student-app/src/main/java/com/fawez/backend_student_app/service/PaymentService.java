package com.fawez.backend_student_app.service;

import com.fawez.backend_student_app.dto.NewPaymentDto;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                                  NewPaymentDto newPaymentDto) throws IOException {
        Path folderPath= Paths.get(System.getProperty("user.home"),"ecole-students","payments");

        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName= UUID.randomUUID().toString();
        Path filePath=Paths.get(System.getProperty("user.home"),"ecole-students","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);

        Student student=studentRepository.findByCode(newPaymentDto.getStudentCode());
        Payment payment=Payment.builder()
                .type(newPaymentDto.getType())
                .student(student)
                .amount(newPaymentDto.getAmount())
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .date(newPaymentDto.getDate())
                .build();
        Payment save= paymentRepository.save(payment);
        return PaymentMapper.toPaymentDto(save);

    }

    public PaymentDto updatePaymentStatus(PaymentStatus status, Long paymentId) {
        Payment payment=paymentRepository.findById(paymentId).get();
         payment.setStatus(status);
        Payment savePayment= paymentRepository.save(payment);
         return PaymentMapper.toPaymentDto(savePayment);

    }

    public List<PaymentDto> findByStudentCode(String code) {
        List<Payment> paymentList=paymentRepository.findByStudentCode(code);
        return paymentList.stream()
                .map(PaymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }
}
