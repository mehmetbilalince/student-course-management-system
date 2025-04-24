package com.spring_student_project.student_course_management.controller;

import com.spring_student_project.student_course_management.dto.request.registration.CreateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.request.registration.UpdateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.response.registration.GetAllRegistrationResponse;
import com.spring_student_project.student_course_management.dto.response.registration.GetByIdRegistrationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/registrations")
@Validated
public interface IRegistrationController {
    @GetMapping
    ResponseEntity<List<GetAllRegistrationResponse>> getAllRegistrations();

    @GetMapping("/{id}")
    ResponseEntity<GetByIdRegistrationResponse> getRegistrationById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<GetByIdRegistrationResponse> addRegistration(@Valid @RequestBody CreateRegistrationRequest createRegistrationRequest);

    @PutMapping("/{id}")
    ResponseEntity<GetByIdRegistrationResponse> updateRegistration(@PathVariable Long id, @Valid @RequestBody UpdateRegistrationRequest updateRegistrationRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRegistration(@PathVariable Long id);
}
