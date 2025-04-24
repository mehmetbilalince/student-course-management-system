package com.spring_student_project.student_course_management.service.impl;

import com.spring_student_project.student_course_management.dto.request.course.CreateCourseRequest;
import com.spring_student_project.student_course_management.dto.request.course.UpdateCourseRequest;
import com.spring_student_project.student_course_management.dto.response.course.GetAllCourseResponse;
import com.spring_student_project.student_course_management.dto.response.course.GetByIdCourseResponse;
import com.spring_student_project.student_course_management.entity.Course;
import com.spring_student_project.student_course_management.exceptions.BaseException;
import com.spring_student_project.student_course_management.exceptions.ErrorMessage;
import com.spring_student_project.student_course_management.exceptions.MessageType;
import com.spring_student_project.student_course_management.mapper.CourseMapper;
import com.spring_student_project.student_course_management.repository.CourseRepository;
import com.spring_student_project.student_course_management.repository.RegistrationRepository;
import com.spring_student_project.student_course_management.service.ICourseService;
import com.spring_student_project.student_course_management.validator.CourseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final CourseMapper courseMapper;
    private final CourseValidator courseValidator;

    @Override
    public List<GetAllCourseResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toGetAllResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetByIdCourseResponse getById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST)));
        return courseMapper.toGetByIdResponse(course);
    }

    @Override
    public GetByIdCourseResponse add(CreateCourseRequest createCourseRequest) {
        courseValidator.validateCourseCode(createCourseRequest.getCourseCode());

        Course course = courseMapper.toEntity(createCourseRequest);
        Course saved = courseRepository.save(course);
        return courseMapper.toGetByIdResponse(saved);
    }

    @Override
    public GetByIdCourseResponse update(Long id, UpdateCourseRequest updateCourseRequest) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        if (!existing.getCourseCode().equals(updateCourseRequest.getCourseCode())) {
            courseValidator.validateCourseCode(updateCourseRequest.getCourseCode());
        }

        courseMapper.updateCourseFromRequest(updateCourseRequest, existing);
        Course updated = courseRepository.save(existing);
        return courseMapper.toGetByIdResponse(updated);
    }


    @Override
    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST)));

        if (registrationRepository.existsByCourseId(id)) {
            throw new BaseException(new ErrorMessage(MessageType.COURSE_HAS_STUDENTS));
        }

        courseRepository.deleteById(id);
    }

}
