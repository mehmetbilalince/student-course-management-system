package com.spring_student_project.student_course_management.dto.response.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdStudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String schoolNumber;
}
