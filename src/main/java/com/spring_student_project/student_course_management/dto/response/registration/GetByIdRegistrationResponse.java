package com.spring_student_project.student_course_management.dto.response.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRegistrationResponse {
    private Long id;
    private Long studentId;
    private String studentName;  // Öğrenci adı
    private Long courseId;
    private String courseName;   // Ders adı
    private String formattedRegistrationDate;  // Formatlı tarih (String türünde)
}
