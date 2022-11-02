package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Page<Course> findByLevelId(Long levelId, Pageable pageable);
}
