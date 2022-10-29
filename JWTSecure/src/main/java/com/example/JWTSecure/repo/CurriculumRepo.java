package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Curriculum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumRepo extends JpaRepository<Curriculum, Long> {

    List<Curriculum> findByCourseId(Long courseId, Pageable pageable);
}
