package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.domain.Activity;
import com.example.JWTSecure.domain.Teacher;
import com.example.JWTSecure.domain.User;

import java.util.List;

public interface TeacherService {

    SearchResultDTO<TeacherDTO> getAllTeacher(TeacherDTO productDTO);
    ResponseStatus addTeacher(AddTeacherDTO addTeacherDTO);
    TeacherDTO viewProfile(TeacherDTO productDTO);
    ResponseStatus editTeacher(AddTeacherDTO addTeacherDTO);
    ResponseStatus createAct(Activity activity);
    ResponseStatus deleteTeacher(Long id);
    List<Teacher> list();

}
