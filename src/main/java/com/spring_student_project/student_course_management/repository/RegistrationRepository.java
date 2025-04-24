package com.spring_student_project.student_course_management.repository;

import com.spring_student_project.student_course_management.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // Öğrencinin bir derse daha önce kayıt olup olmadığını kontrol eder
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    // Öğrencinin toplamda kaç ders kaydı olduğunu sayar
    long countByStudentId(Long studentId);

    // Öğrencinin kaydının bulunduğu herhangi bir derse ait kayıt var mı kontrol eder
    boolean existsByStudentId(Long studentId);

    boolean existsByCourseId(Long courseId);
}
