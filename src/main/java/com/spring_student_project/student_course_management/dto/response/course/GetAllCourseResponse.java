package com.spring_student_project.student_course_management.dto.response.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCourseResponse {
    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credit;
}
