package com.spring_student_project.student_course_management.validator;

import com.spring_student_project.student_course_management.repository.StudentRepository;
import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;

    public void validateEmail(String email) {
        if (studentRepository.existsByEmail(email)) {
            throw new BaseException(new ErrorMessage(MessageType.EMAIL_ALREADY_EXISTS));
        }
    }

    public void validateSchoolNumber(String schoolNumber) {
        if (studentRepository.existsBySchoolNumber(schoolNumber)) {
            throw new BaseException(new ErrorMessage(MessageType.SCHOOL_NUMBER_ALREADY_EXISTS));
        }
    }

    public List<ErrorMessage> validateCreateOrUpdateStudent(String email, String schoolNumber, String existingEmail, String existingSchoolNumber) {
        List<ErrorMessage> errorMessages = new ArrayList<>();

        if (!email.equals(existingEmail) && studentRepository.existsByEmail(email)) {
            errorMessages.add(new ErrorMessage(MessageType.EMAIL_ALREADY_EXISTS));
        }

        if (!schoolNumber.equals(existingSchoolNumber) && studentRepository.existsBySchoolNumber(schoolNumber)) {
            errorMessages.add(new ErrorMessage(MessageType.SCHOOL_NUMBER_ALREADY_EXISTS));
        }

        return errorMessages;
    }
}
