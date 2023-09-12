package com.jdev.beans;

import com.github.javafaker.Faker;
import com.jdev.models.Student;
import com.jdev.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Log4j2
@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final Faker faker;
    private final StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        IntStream.generate(() -> 0)
                .limit(100)
                .forEach(e -> {
                    Student student = Student.builder()
                            .name(faker.name().name())
                            .phone(faker.phoneNumber().subscriberNumber(10))
                            .build();
                    repository.saveAndFlush(student);
                    log.info("Student no. {} saved, {}", e, student);
                });
    }
}
