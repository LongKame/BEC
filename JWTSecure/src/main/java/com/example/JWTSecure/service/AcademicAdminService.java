package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.domain.Quiz;
import com.example.JWTSecure.domain.Room;

import java.util.List;

public interface AcademicAdminService {

    List<Quiz> getQuiz(Long levelId);
    SearchResultDTO<RoomDTO> getRoom(RoomDTO roomDTO);
    SearchResultDTO<AcademicAdminDTO> getAllAcad(AcademicAdminDTO academicAdminDTO);
    ResponseStatus addAcad(AddAcademicAdminDTO addAcademicAdminDTO);
    AcademicAdminDTO viewProfile(AcademicAdminDTO academicAdminDTO);
    ResponseStatus editAcad(AddAcademicAdminDTO addAcademicAdminDTO);
    ResponseStatus deleteAcad(AddAcademicAdminDTO addAcademicAdminDTO);
}
