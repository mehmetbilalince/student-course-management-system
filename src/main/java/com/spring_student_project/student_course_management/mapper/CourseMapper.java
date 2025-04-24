package com.spring_student_project.student_course_management.mapper;

import com.spring_student_project.student_course_management.dto.request.course.CreateCourseRequest;
import com.spring_student_project.student_course_management.dto.request.course.UpdateCourseRequest;
import com.spring_student_project.student_course_management.dto.response.course.GetAllCourseResponse;
import com.spring_student_project.student_course_management.dto.response.course.GetByIdCourseResponse;
import com.spring_student_project.student_course_management.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CreateCourseRequest request);

    Course toEntity(UpdateCourseRequest request);

    GetAllCourseResponse toGetAllResponse(Course course);

    GetByIdCourseResponse toGetByIdResponse(Course course);

    void updateCourseFromRequest(UpdateCourseRequest request, @MappingTarget Course course);
}
