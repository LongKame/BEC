package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByUserId(Long id);


}