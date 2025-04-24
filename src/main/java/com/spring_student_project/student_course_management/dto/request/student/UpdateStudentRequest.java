package com.spring_student_project.student_course_management.dto.request.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 100, message = "First name should not exceed 100 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name should not exceed 100 characters")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "School number cannot be blank")
    @Size(max = 100, message = "School number should not exceed 100 characters")
    private String schoolNumber;
}
