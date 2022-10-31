package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
