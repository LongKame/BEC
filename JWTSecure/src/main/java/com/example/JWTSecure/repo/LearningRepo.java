package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Learning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRepo extends JpaRepository<Learning, Long> {
    Long countByCurriculumId(Long id);
}
