package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.AcademicAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicAdminRepo extends JpaRepository<AcademicAdmin, Long> {
    AcademicAdmin findByUserId(Long id);
}