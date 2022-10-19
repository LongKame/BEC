package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Quiz;
import com.example.JWTSecure.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {
    @Override
    List<Quiz> findAll();
    List<Quiz> findByLevelId(Long levelId);
}
