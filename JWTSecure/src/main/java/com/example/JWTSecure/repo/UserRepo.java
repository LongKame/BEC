package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Role;
import com.example.JWTSecure.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsername(String user);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findTopByOrderByIdDesc();
}

