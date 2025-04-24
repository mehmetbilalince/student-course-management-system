package com.spring_student_project.student_course_management.controller.impl;

import com.spring_student_project.student_course_management.controller.IRegistrationController;
import com.spring_student_project.student_course_management.dto.request.registration.CreateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.request.registration.UpdateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.response.registration.GetAllRegistrationResponse;
import com.spring_student_project.student_course_management.dto.response.registration.GetByIdRegistrationResponse;
import com.spring_student_project.student_course_management.service.IRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RegistrationsControllerImpl implements IRegistrationController {
    private final IRegistrationService registrationService;

    @Override
    public ResponseEntity<List<GetAllRegistrationResponse>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAll());
    }

    @Override
    public ResponseEntity<GetByIdRegistrationResponse> getRegistrationById(Long id) {
        return ResponseEntity.ok(registrationService.getById(id));
    }

    @Override
    public ResponseEntity<GetByIdRegistrationResponse> addRegistration(CreateRegistrationRequest createRegistrationRequest) {
        return ResponseEntity.status(201).body(registrationService.add(createRegistrationRequest));
    }

    @Override
    public ResponseEntity<GetByIdRegistrationResponse> updateRegistration(Long id, UpdateRegistrationRequest updateRegistrationRequest) {
        return ResponseEntity.ok(registrationService.update(id, updateRegistrationRequest));
    }

    @Override
    public ResponseEntity<Void> deleteRegistration(Long id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
