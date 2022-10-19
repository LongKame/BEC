package com.example.JWTSecure.service;
import com.example.JWTSecure.DTO.*;


public interface StudentService {

    StudentDTO getStudent(StudentDTO studentDTO);
    SearchResultDTO<StudentDTO> getAllStudent(StudentDTO studentDTO);
    ResponseStatus addStudent(AddStudentDTO addStudentDTO);
    ResponseStatus editStudent(AddStudentDTO addStudentDTO);
}
