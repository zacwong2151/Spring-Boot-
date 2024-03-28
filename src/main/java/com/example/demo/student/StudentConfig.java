package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
/*
    The CommandLineRunner is an interface in Spring Boot. When a class implements this interface, Spring Boot will
    automatically run its run method after loading the application context. Usually, we use this CommandLineRunner to
    perform startup tasks like user or database initialization, seeding, or other startup activities.
 */
@Configuration
public class StudentConfig {
    @Bean // the combination of CommandLineRunner interface and @Bean annotation means that the function below is
            // run automatically during startup
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 7)
            );
            studentRepo.saveAll(
                    List.of(mariam, alex)
            );
        };
    }

}
