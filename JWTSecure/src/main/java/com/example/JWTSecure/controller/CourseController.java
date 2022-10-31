package com.example.JWTSecure.controller;

import com.example.JWTSecure.DTO.CourseDTO;
import com.example.JWTSecure.domain.Course;
import com.example.JWTSecure.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/course")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @PostMapping("")
    public CourseDTO createCourse(@RequestBody Course course) {
        if (course.getId() != null) throw new RuntimeException("Id of Course must null");
        return courseService.createCourse(course);
    }

    @PutMapping("")
    public CourseDTO updateCourse(@RequestBody Course course) {
        if (course.getId() == null) throw new RuntimeException("Id of Course must not null");
        return courseService.updateCourse(course);
    }

    @GetMapping("{id}")
    public CourseDTO getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping("")
    public CourseDTO getAllCourse(@RequestParam Integer page) {
        return courseService.getAllCourse(page);
    }

}
