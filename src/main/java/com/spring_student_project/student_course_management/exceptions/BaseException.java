package com.spring_student_project.student_course_management.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseException extends RuntimeException {
    private final List<ErrorMessage> errorMessages;

    public BaseException(List<ErrorMessage> errorMessages) {
        super(errorMessages.stream()
                .map(ErrorMessage::getMessage)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Bir hata oluştu"));
        this.errorMessages = errorMessages;
    }

    public BaseException(ErrorMessage errorMessage) {
        this(List.of(errorMessage));
    }
}
