package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

}