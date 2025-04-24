package com.spring_student_project.student_course_management.service;

import com.spring_student_project.student_course_management.dto.request.registration.CreateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.request.registration.UpdateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.response.registration.GetAllRegistrationResponse;
import com.spring_student_project.student_course_management.dto.response.registration.GetByIdRegistrationResponse;

import java.util.List;

public interface IRegistrationService {
    List<GetAllRegistrationResponse> getAll();

    GetByIdRegistrationResponse getById(long id);

    GetByIdRegistrationResponse add(CreateRegistrationRequest createRegistrationRequest);

    GetByIdRegistrationResponse update(Long id, UpdateRegistrationRequest updateRegistrationRequest);

    void delete(Long id);
}
