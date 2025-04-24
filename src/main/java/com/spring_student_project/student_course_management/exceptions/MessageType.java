package com.spring_student_project.student_course_management.exceptions;

public enum MessageType {
    NO_RECORD_EXIST("1001", "Kayıt Bulunamadı!"),
    GENERAL_EXCEPTION("9999", "Genel Bir Hata Oluştu!"),
    VALIDATION_EXCEPTION("1002", "Doğrulama Hatası!"),
    EMAIL_ALREADY_EXISTS("1003", "Bu e-posta adresiyle kayıtlı bir öğrenci zaten mevcut!"),
    SCHOOL_NUMBER_ALREADY_EXISTS("1004", "Bu okul numarasıyla kayıtlı bir öğrenci zaten mevcut!"),
    COURSE_CODE_ALREADY_EXISTS("1005", "Bu ders koduyla kayıtlı bir ders zaten mevcut!"),
    DUPLICATE_REGISTRATION("1006", "Bu öğrenci zaten bu derse kayıtlı!"),
    MAX_COURSES_LIMIT("1007", "Bir öğrenci en fazla 6 derse kayıt olabilir!"),
    REGISTRATION_ALREADY_EXISTS("1008", "Bu öğrenci zaten bu derse kayıtlı!"),
    MAX_COURSE_LIMIT_REACHED("1009", "Bir öğrenci en fazla 6 derse kayıt olabilir!"),
    REGISTRATION_EXISTS("1008", "Bu öğrenci bir derse kayıtlı olduğu için silinemez!"),
    COURSE_HAS_STUDENTS("1008", "Bu derse kayıtlı öğrenciler bulunduğu için silme işlemi yapılamaz!");



    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
