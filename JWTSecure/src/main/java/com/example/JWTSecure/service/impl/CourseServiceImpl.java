package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.CourseDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Course;
import com.example.JWTSecure.mapper.CourseMapper;
import com.example.JWTSecure.repo.CourseRepo;
import com.example.JWTSecure.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepo courseRepo;

    @Override
    public CourseDTO createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        return CourseMapper.toDto(courseRepo.save(course));
    }

    @Override
    public CourseDTO updateCourse(Course course) {
        course.setUpdatedAt(LocalDateTime.now());
        return CourseMapper.toDto(courseRepo.save(course));
    }

    @Override
    public CourseDTO getCourse(Long id) {
        return courseRepo.findById(id).map(CourseMapper::toDto).orElse(null);
    }

    @Override
    public SearchResultDTO<CourseDTO> getAllCourse(Integer page) {
        Page<Course> courses =  courseRepo.findAll(PageRequest.of(page, 20));
        return convertPageResult(courses);
    }

    @Override
    public SearchResultDTO<CourseDTO> searchByLevel(Long levelId, Integer page) {
        Page<Course> courses =  courseRepo.findByLevelId(levelId, PageRequest.of(page, 20));
        return convertPageResult(courses);
    }

    private SearchResultDTO<CourseDTO> convertPageResult(Page<Course> courses) {
        if(courses.isEmpty() && courses.getTotalElements() == 0L) {
            return SearchResultDTO.defaultNotFound();
        }
        SearchResultDTO<CourseDTO> rs = SearchResultDTO.defaultSuccess();
        rs.setResultData(courses.get().map(CourseMapper::toDto).collect(Collectors.toList()));
        rs.setTotalRecordNoLimit((int) courses.getTotalElements());
        return rs;
    }
}
