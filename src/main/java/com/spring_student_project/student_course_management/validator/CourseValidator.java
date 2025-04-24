package com.spring_student_project.student_course_management.validator;

import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import com.spring_student_project.student_course_management.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseValidator {
    private final CourseRepository courseRepository;

    public void validateCourseCode(String courseCode){
        if(courseRepository.existsByCourseCode(courseCode)){
            throw new BaseException(new ErrorMessage(MessageType.COURSE_CODE_ALREADY_EXISTS));
        }
    }

}
