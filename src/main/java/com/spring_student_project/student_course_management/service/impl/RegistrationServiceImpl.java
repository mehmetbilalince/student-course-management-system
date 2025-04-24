package com.spring_student_project.student_course_management.service.impl;

import com.spring_student_project.student_course_management.dto.request.registration.CreateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.request.registration.UpdateRegistrationRequest;
import com.spring_student_project.student_course_management.dto.response.registration.GetAllRegistrationResponse;
import com.spring_student_project.student_course_management.dto.response.registration.GetByIdRegistrationResponse;
import com.spring_student_project.student_course_management.entity.Course;
import com.spring_student_project.student_course_management.entity.Registration;
import com.spring_student_project.student_course_management.entity.Student;
import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import com.spring_student_project.student_course_management.mapper.RegistrationMapper;
import com.spring_student_project.student_course_management.repository.CourseRepository;
import com.spring_student_project.student_course_management.repository.RegistrationRepository;
import com.spring_student_project.student_course_management.repository.StudentRepository;
import com.spring_student_project.student_course_management.service.IRegistrationService;
import com.spring_student_project.student_course_management.validator.RegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements IRegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RegistrationMapper registrationMapper;
    private final RegistrationValidator registrationValidator;


    @Override
    public List<GetAllRegistrationResponse> getAll() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations.stream()
                .map(registration -> {
                    GetAllRegistrationResponse response = registrationMapper.toGetAllResponse(registration);
                    response.setStudentId(registration.getStudent().getId());
                    response.setCourseId(registration.getCourse().getId());
                    response.setStudentName(registration.getStudent().getName());
                    response.setCourseName(registration.getCourse().getCourseName());

                    response.setFormattedRegistrationDate(formatDate(registration.getRegistrationDate()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public GetByIdRegistrationResponse getById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(registration);
        response.setFormattedRegistrationDate(formatDate(registration.getRegistrationDate()));
        return response;
    }

    @Override
    public GetByIdRegistrationResponse add(CreateRegistrationRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        if (registrationRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new BaseException(new ErrorMessage(MessageType.REGISTRATION_ALREADY_EXISTS));
        }

        if (registrationRepository.countByStudentId(request.getStudentId()) >= 6) {
            throw new BaseException(new ErrorMessage(MessageType.MAX_COURSE_LIMIT_REACHED));
        }

        Registration registration = Registration.builder()
                .student(student)
                .course(course)
                .registrationDate(LocalDateTime.now())
                .build();

        Registration saved = registrationRepository.save(registration);
        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(saved);

        response.setFormattedRegistrationDate(formatDate(saved.getRegistrationDate()));
        return response;
    }

    /**
     * Mevcut kaydı günceller
     */
    @Override
    public GetByIdRegistrationResponse update(Long id, UpdateRegistrationRequest request) {
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        existing.setStudent(student);
        existing.setCourse(course);
        existing.setRegistrationDate(LocalDateTime.parse(request.getRegistrationDate()));

        Registration updated = registrationRepository.save(existing);
        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(updated);

        response.setFormattedRegistrationDate(formatDate(updated.getRegistrationDate()));
        return response;
    }

    @Override
    public void delete(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }
        registrationRepository.deleteById(id);
    }

    private String formatDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
