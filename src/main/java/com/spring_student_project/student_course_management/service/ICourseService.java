package com.spring_student_project.student_course_management.service;

import com.spring_student_project.student_course_management.dto.request.course.CreateCourseRequest;
import com.spring_student_project.student_course_management.dto.request.course.UpdateCourseRequest;
import com.spring_student_project.student_course_management.dto.response.course.GetAllCourseResponse;
import com.spring_student_project.student_course_management.dto.response.course.GetByIdCourseResponse;

import java.util.List;

public interface ICourseService {
    List<GetAllCourseResponse> getAll();

    GetByIdCourseResponse getById(long id);

    GetByIdCourseResponse add(CreateCourseRequest createCourseRequest);

    GetByIdCourseResponse update(Long id, UpdateCourseRequest updateCourseRequest);

    void delete(Long id);
}
