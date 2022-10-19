package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
    Guest findByUserId(Long id);
}