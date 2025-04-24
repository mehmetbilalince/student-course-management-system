package com.spring_student_project.student_course_management.service.impl;

import com.spring_student_project.student_course_management.dto.request.student.CreateStudentRequest;
import com.spring_student_project.student_course_management.dto.request.student.UpdateStudentRequest;
import com.spring_student_project.student_course_management.dto.response.student.GetAllStudentResponse;
import com.spring_student_project.student_course_management.dto.response.student.GetByIdStudentResponse;
import com.spring_student_project.student_course_management.entity.Student;
import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import com.spring_student_project.student_course_management.mapper.StudentMapper;
import com.spring_student_project.student_course_management.repository.RegistrationRepository;
import com.spring_student_project.student_course_management.repository.StudentRepository;
import com.spring_student_project.student_course_management.service.IStudentService;
import com.spring_student_project.student_course_management.validator.StudentValidator;  // Import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;
    private final StudentMapper studentMapper;
    private final StudentValidator studentValidator;  // Injected Validator

    @Override
    public List<GetAllStudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toGetAllResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetByIdStudentResponse getById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        return studentMapper.toGetByIdResponse(student);
    }

    @Override
    public GetByIdStudentResponse add(CreateStudentRequest createStudentRequest) {
        // E-posta ve okul numarasını kontrol et
        List<ErrorMessage> errorMessages = studentValidator.validateCreateOrUpdateStudent(
                createStudentRequest.getEmail(),
                createStudentRequest.getSchoolNumber(),
                "",
                ""
        );

        if (!errorMessages.isEmpty()) {
            throw new BaseException(errorMessages);
        }

        Student student = studentMapper.toEntity(createStudentRequest);
        Student saved = studentRepository.save(student);
        return studentMapper.toGetByIdResponse(saved);
    }

    @Override
    public GetByIdStudentResponse update(Long id, UpdateStudentRequest updateStudentRequest) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        List<ErrorMessage> errorMessages = studentValidator.validateCreateOrUpdateStudent(
                updateStudentRequest.getEmail(),
                updateStudentRequest.getSchoolNumber(),
                existing.getEmail(),
                existing.getSchoolNumber()
        );

        if (!errorMessages.isEmpty()) {
            throw new BaseException(errorMessages);
        }

        studentMapper.updateStudentFromRequest(updateStudentRequest, existing);

        Student updated = studentRepository.save(existing);
        return studentMapper.toGetByIdResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

        if (registrationRepository.existsByStudentId(id)) {
            throw new BaseException(new ErrorMessage(MessageType.REGISTRATION_EXISTS));
        }

        studentRepository.deleteById(id);
    }

}
