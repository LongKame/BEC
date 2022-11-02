package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.ClassDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;


public interface ClassService {

    SearchResultDTO<ClassDTO> getAllClass(ClassDTO classDTO);

}
