package com.spring_student_project.student_course_management.controller.impl;

import com.spring_student_project.student_course_management.controller.IStudentController;
import com.spring_student_project.student_course_management.dto.request.student.CreateStudentRequest;
import com.spring_student_project.student_course_management.dto.request.student.UpdateStudentRequest;
import com.spring_student_project.student_course_management.dto.response.student.GetAllStudentResponse;
import com.spring_student_project.student_course_management.dto.response.student.GetByIdStudentResponse;
import com.spring_student_project.student_course_management.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentControllerImpl implements IStudentController {
    private final IStudentService studentService;

    @Override
    public ResponseEntity<List<GetAllStudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @Override
    public ResponseEntity<GetByIdStudentResponse> getStudentById(Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @Override
    public ResponseEntity<GetByIdStudentResponse> addStudent(CreateStudentRequest createStudentRequest) {
        return ResponseEntity.status(201).body(studentService.add(createStudentRequest));
    }

    @Override
    public ResponseEntity<GetByIdStudentResponse> updateStudent(Long id, UpdateStudentRequest updateStudentRequest) {
        return ResponseEntity.ok(studentService.update(id, updateStudentRequest)); // ID'yi URL'den alıyoruz.
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
