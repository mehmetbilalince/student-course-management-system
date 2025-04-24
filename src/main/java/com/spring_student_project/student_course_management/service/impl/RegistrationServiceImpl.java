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

    /**
     * Tüm kayıtları getirir
     */
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

                    // Sadece formatlı tarihi ayarla
                    response.setFormattedRegistrationDate(formatDate(registration.getRegistrationDate()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    /**
     * ID'ye göre kayıt getirir
     */
    @Override
    public GetByIdRegistrationResponse getById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(registration);
        // Sadece formatlı tarihi ayarla
        response.setFormattedRegistrationDate(formatDate(registration.getRegistrationDate()));
        return response;
    }

    /**
     * Yeni bir kayıt ekler
     */
    @Override
    public GetByIdRegistrationResponse add(CreateRegistrationRequest request) {
        // Student ve Course kontrolü
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        // Öğrencinin zaten bu derse kaydolup kaydolmadığını kontrol et
        if (registrationRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new BaseException(new ErrorMessage(MessageType.REGISTRATION_ALREADY_EXISTS));
        }

        // Öğrencinin daha fazla derse kayıt olamaması için toplam kayıt sayısını kontrol et
        if (registrationRepository.countByStudentId(request.getStudentId()) >= 6) {
            throw new BaseException(new ErrorMessage(MessageType.MAX_COURSE_LIMIT_REACHED));
        }

        // Yeni kayıt oluşturuluyor
        Registration registration = Registration.builder()
                .student(student)
                .course(course)
                .registrationDate(LocalDateTime.now()) // Otomatik tarih
                .build();

        Registration saved = registrationRepository.save(registration); // Veritabanına kayıt
        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(saved);

        // Sadece formatlı tarihi ayarla
        response.setFormattedRegistrationDate(formatDate(saved.getRegistrationDate()));
        return response;
    }

    /**
     * Mevcut kaydı günceller
     */
    @Override
    public GetByIdRegistrationResponse update(Long id, UpdateRegistrationRequest request) {
        // Mevcut kayıt kontrolü
        Registration existing = registrationRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        // Student ve Course kontrolü
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        // Güncellemeleri yap
        existing.setStudent(student);
        existing.setCourse(course);
        existing.setRegistrationDate(LocalDateTime.parse(request.getRegistrationDate())); // Dışarıdan gelen tarih set edilir

        Registration updated = registrationRepository.save(existing); // Güncellenmiş hali kaydedilir
        GetByIdRegistrationResponse response = registrationMapper.toGetByIdResponse(updated);

        // Sadece formatlı tarihi ayarla
        response.setFormattedRegistrationDate(formatDate(updated.getRegistrationDate()));
        return response;
    }

    /**
     * Kaydı siler
     */
    @Override
    public void delete(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }
        registrationRepository.deleteById(id);
    }

    /**
     * Tarihi formatlamak için yardımcı metod
     */
    private String formatDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter); // Formatı istediğiniz gibi değiştirebilirsiniz
    }
}
