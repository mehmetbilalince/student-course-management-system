package com.spring_student_project.student_course_management.dto.request.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCourseRequest {

    @NotBlank(message = "Course name cannot be blank")
    @Size(max = 100, message = "Course name should not exceed 100 characters")
    private String courseName;

    @NotBlank(message = "Course code cannot be blank")
    @Size(max = 50, message = "Course code should not exceed 50 characters")
    private String courseCode;

    @NotNull(message = "Credit cannot be null")
    @Min(value = 1, message = "Credit must be at least 1")
    @Max(value = 10, message = "Credit must not exceed 10")
    private Integer credit;
}
