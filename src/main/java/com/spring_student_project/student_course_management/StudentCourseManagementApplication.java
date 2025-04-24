package com.spring_student_project.student_course_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.spring_student_project.student_course_management")

public class StudentCourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseManagementApplication.class, args);
	}

}
