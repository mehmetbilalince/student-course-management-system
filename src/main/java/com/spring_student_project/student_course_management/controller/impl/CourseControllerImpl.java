package com.spring_student_project.student_course_management.controller.impl;

import com.spring_student_project.student_course_management.controller.ICourseController;
import com.spring_student_project.student_course_management.dto.request.course.CreateCourseRequest;
import com.spring_student_project.student_course_management.dto.request.course.UpdateCourseRequest;
import com.spring_student_project.student_course_management.dto.response.course.GetAllCourseResponse;
import com.spring_student_project.student_course_management.dto.response.course.GetByIdCourseResponse;
import com.spring_student_project.student_course_management.service.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseControllerImpl implements ICourseController {
    private final ICourseService courseService;

    @Override
    public ResponseEntity<List<GetAllCourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @Override
    public ResponseEntity<GetByIdCourseResponse> getCourseById(Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @Override
    public ResponseEntity<GetByIdCourseResponse> addCourse(CreateCourseRequest createCourseRequest) {
        return ResponseEntity.status(201).body(courseService.add(createCourseRequest));
    }

    @Override
    public ResponseEntity<GetByIdCourseResponse> updateCourse(Long id, UpdateCourseRequest updateCourseRequest) {
        return ResponseEntity.ok(courseService.update(id, updateCourseRequest)); // ID'yi URL'den alıyoruz.
    }

    @Override
    public ResponseEntity<Void> deleteCourse(Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
