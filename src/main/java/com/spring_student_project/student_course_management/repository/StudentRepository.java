package com.spring_student_project.student_course_management.repository;

import com.spring_student_project.student_course_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    boolean existsBySchoolNumber(String schoolNumber);
}
