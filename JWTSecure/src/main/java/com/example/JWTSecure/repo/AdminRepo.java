package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByUserId(Long id);
    Optional<Admin> findById(Long id);
}