package com.example.JWTSecure.mapper;

import com.example.JWTSecure.DTO.CourseDTO;
import com.example.JWTSecure.domain.Course;

public class CourseMapper {
    private CourseMapper() {
        throw new IllegalCallerException("Utilities class");
    }

    public static CourseDTO toDto(Course from) {
        CourseDTO to = new CourseDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setLevelId(from.getLevelId());
        to.setCurriculumId(from.getCurriculumId());
        return to;
    }
}
