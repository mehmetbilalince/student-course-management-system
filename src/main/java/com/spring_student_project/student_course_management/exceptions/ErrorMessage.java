package com.spring_student_project.student_course_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private final String code;
    private final String message;

    // MessageType ile ErrorMessage oluştur
    public ErrorMessage(MessageType messageType) {
        this.code = messageType.getCode();
        this.message = messageType.getMessage();
    }
}



