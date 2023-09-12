package com.jdev.controllers;

import com.jdev.models.Student;
import com.jdev.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

    private final StudentRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Sort sort = Sort.sort(Student.class).by(Student::getName).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable)
                .map(e -> e)
                .toList();
    }


}
