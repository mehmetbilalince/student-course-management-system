package com.spring_student_project.student_course_management.repository;

import com.spring_student_project.student_course_management.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    long countByStudentId(Long studentId);

    boolean existsByStudentId(Long studentId);

    boolean existsByCourseId(Long courseId);
}
