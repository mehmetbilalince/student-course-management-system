package com.spring_student_project.student_course_management.mapper;

import com.spring_student_project.student_course_management.dto.request.registration.CreateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.request.registration.UpdateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.response.registration.GetAllRegistrationResponse;
import com.spring_student_project.student_course_management.dto.response.registration.GetByIdRegistrationResponse;
import com.spring_student_project.student_course_management.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration toEntity(CreateRegistrationRequest request);

    Registration toEntity(UpdateRegistrationRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "courseName", source = "course.courseName")
    @Mapping(target = "formattedRegistrationDate", source = "registrationDate")
    GetAllRegistrationResponse toGetAllResponse(Registration registration);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "courseName", source = "course.courseName")
    @Mapping(target = "formattedRegistrationDate", source = "registrationDate")
    GetByIdRegistrationResponse toGetByIdResponse(Registration registration);

    void updateRegistrationFromRequest(UpdateRegistrationRequest request, @MappingTarget Registration registration);
}
