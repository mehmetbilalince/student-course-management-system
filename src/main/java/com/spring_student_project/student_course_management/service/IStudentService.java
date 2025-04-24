package com.spring_student_project.student_course_management.service;

import com.spring_student_project.student_course_management.dto.request.student.CreateStudentRequest;
import com.spring_student_project.student_course_management.dto.request.student.UpdateStudentRequest;
import com.spring_student_project.student_course_management.dto.response.student.GetAllStudentResponse;
import com.spring_student_project.student_course_management.dto.response.student.GetByIdStudentResponse;

import java.util.List;

public interface IStudentService {
    List<GetAllStudentResponse> getAll();

    GetByIdStudentResponse getById(long id);

    GetByIdStudentResponse add(CreateStudentRequest createStudentRequest);

    GetByIdStudentResponse update(Long id, UpdateStudentRequest updateStudentRequest);

    void delete(Long id);
}
