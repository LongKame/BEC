package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepo extends JpaRepository<Curriculum, Long> {

    Page<Curriculum> findByCourseId(Long courseId, Pageable pageable);
}
