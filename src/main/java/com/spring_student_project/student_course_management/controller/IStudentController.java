package com.spring_student_project.student_course_management.controller;

import com.spring_student_project.student_course_management.dto.request.student.CreateStudentRequest;
import com.spring_student_project.student_course_management.dto.request.student.UpdateStudentRequest;
import com.spring_student_project.student_course_management.dto.response.student.GetAllStudentResponse;
import com.spring_student_project.student_course_management.dto.response.student.GetByIdStudentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/students")
@Validated
public interface IStudentController {
    @GetMapping
    ResponseEntity<List<GetAllStudentResponse>> getAllStudents();

    @GetMapping("/{id}")
    ResponseEntity<GetByIdStudentResponse> getStudentById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<GetByIdStudentResponse> addStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest);

    @PutMapping("/{id}")
    ResponseEntity<GetByIdStudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequest updateStudentRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStudent(@PathVariable Long id);
}
