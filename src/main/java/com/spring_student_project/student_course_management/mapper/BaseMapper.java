package com.spring_student_project.student_course_management.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateEntity(@MappingTarget E entity, E updatedEntity);
}
