package com.fawez.backend_student_app;

import com.fawez.backend_student_app.entities.Payment;
import com.fawez.backend_student_app.entities.PaymentType;
import com.fawez.backend_student_app.entities.Student;
import com.fawez.backend_student_app.repository.PaymentRepository;
import com.fawez.backend_student_app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendStudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendStudentAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository){
		return arg->{
			studentRepository.save(Student.builder()
							.code("12345")
							.id(UUID.randomUUID().toString())
							.firstName("Fawez")

					.build());
			studentRepository.save(Student.builder()
							.code("123")
					.id(UUID.randomUUID().toString())
					.firstName("Ali")

					.build());
			studentRepository.save(Student.builder()
							.code("12347")
					.id(UUID.randomUUID().toString())
					.firstName("salm")

					.build());

			PaymentType [] paymentTypes=PaymentType.values();
			Random random = new Random();

			studentRepository.findAll().forEach(st -> {
				for (int i = 0; i < 10; i++) {
					int index=random.nextInt(paymentTypes.length);

					Payment payment= Payment.builder()
							.amount(1000+ (int) (Math.random() * 10000))
							.date(LocalDate.now())
							.type(paymentTypes[index])
							.file(UUID.randomUUID().toString())
							.student(st)

							.build();
					paymentRepository.save(payment);

				}
			});

		};

	}
}
