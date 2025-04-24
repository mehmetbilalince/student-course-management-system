package com.spring_student_project.student_course_management.controller;

import com.spring_student_project.student_course_management.dto.request.course.CreateCourseRequest;
import com.spring_student_project.student_course_management.dto.request.course.UpdateCourseRequest;
import com.spring_student_project.student_course_management.dto.response.course.GetAllCourseResponse;
import com.spring_student_project.student_course_management.dto.response.course.GetByIdCourseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/courses")
@Validated
public interface ICourseController {
    @GetMapping
    ResponseEntity<List<GetAllCourseResponse>> getAllCourses();

    @GetMapping("/{id}")
    ResponseEntity<GetByIdCourseResponse> getCourseById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<GetByIdCourseResponse> addCourse(@Valid @RequestBody CreateCourseRequest createCourseRequest);

    @PutMapping("/{id}")
    ResponseEntity<GetByIdCourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest updateCourseRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@PathVariable Long id);
}
