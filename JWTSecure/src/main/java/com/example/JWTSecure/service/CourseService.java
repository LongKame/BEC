package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.CourseDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Course;

public interface CourseService {
    CourseDTO createCourse(Course course);

    CourseDTO updateCourse(Course course);

    CourseDTO getCourse(Long id);

    SearchResultDTO<CourseDTO> getAllCourse(Integer page);

    SearchResultDTO<CourseDTO> searchByLevel(Long levelId, Integer page);
}
