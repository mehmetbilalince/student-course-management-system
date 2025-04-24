package com.spring_student_project.student_course_management.mapper;

import com.spring_student_project.student_course_management.dto.request.student.CreateStudentRequest;
import com.spring_student_project.student_course_management.dto.request.student.UpdateStudentRequest;
import com.spring_student_project.student_course_management.dto.response.student.GetAllStudentResponse;
import com.spring_student_project.student_course_management.dto.response.student.GetByIdStudentResponse;
import com.spring_student_project.student_course_management.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(CreateStudentRequest request);

    Student toEntity(UpdateStudentRequest request);

    GetAllStudentResponse toGetAllResponse(Student student);

    GetByIdStudentResponse toGetByIdResponse(Student student);

    void updateStudentFromRequest(UpdateStudentRequest request, @MappingTarget Student student);
}