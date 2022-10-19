package com.example.JWTSecure.repo.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepoImpl {

    @PersistenceContext
    private EntityManager entityManager;

}
