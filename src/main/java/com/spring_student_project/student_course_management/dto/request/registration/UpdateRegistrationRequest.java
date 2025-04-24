package com.spring_student_project.student_course_management.dto.request.registration;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRegistrationRequest {

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Registration date cannot be null")
    private String registrationDate;
}
