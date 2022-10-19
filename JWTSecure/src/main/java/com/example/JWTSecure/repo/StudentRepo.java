package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByUserId(Long id);
}