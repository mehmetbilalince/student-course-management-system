package com.spring_student_project.student_course_management.validator;

import com.spring_student_project.student_course_management.repository.RegistrationRepository;
import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationValidator {

    private final RegistrationRepository registrationRepository;

    public void validateDuplicateRegistration(Long studentId, Long courseId) {
        if (registrationRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new BaseException(new ErrorMessage(MessageType.DUPLICATE_REGISTRATION));
        }
    }

    public void validateMaxCourses(Long studentId) {
        long count = registrationRepository.countByStudentId(studentId);
        if (count >= 6) {
            throw new BaseException(new ErrorMessage(MessageType.MAX_COURSES_LIMIT));
        }
    }

    public void validateRegistration(Long studentId, Long courseId) {
        validateDuplicateRegistration(studentId, courseId);
        validateMaxCourses(studentId);
    }
}
