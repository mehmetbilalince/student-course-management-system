package com.spring_student_project.student_course_management.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseException extends RuntimeException {
    private final List<ErrorMessage> errorMessages;

    // BaseException, birden fazla hata mesajı alacak şekilde güncellendi
    public BaseException(List<ErrorMessage> errorMessages) {
        super(errorMessages.stream()
                .map(ErrorMessage::getMessage)
                .reduce((a, b) -> a + ", " + b)  // Birleştirilmiş hata mesajları
                .orElse("Bir hata oluştu"));
        this.errorMessages = errorMessages;
    }

    // Tek bir hata mesajı alacak başka bir constructor da eklenebilir
    public BaseException(ErrorMessage errorMessage) {
        this(List.of(errorMessage));
    }
}
