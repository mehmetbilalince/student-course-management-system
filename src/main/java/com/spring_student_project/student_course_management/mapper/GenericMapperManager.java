package com.spring_student_project.student_course_management.mapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenericMapperManager {

    private final Map<Class<?>, Object> mapperMap = new HashMap<>();

    // List<Object> alacak şekilde constructor'ı güncelliyoruz
    public GenericMapperManager(List<Object> mappers) {
        for (Object mapper : mappers) {
            Class<?>[] interfaces = mapper.getClass().getInterfaces();
            if (interfaces.length > 0) {
                Class<?> mapperInterface = interfaces[0];
                mapperMap.put(mapperInterface, mapper);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) mapperMap.get(mapperClass);
    }
}

