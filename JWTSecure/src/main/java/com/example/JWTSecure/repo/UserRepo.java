package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Role;
import com.example.JWTSecure.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsername(String user);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findTopByOrderByIdDesc();
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.active = ?2 WHERE user.id = ?1")
    int deActive(boolean active, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User a SET a.enabled=true WHERE a.email=?1")
    int enableAppUser(String email);
}

