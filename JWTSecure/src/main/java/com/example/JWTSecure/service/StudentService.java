package com.example.JWTSecure.service;
import com.example.JWTSecure.DTO.*;

import java.util.List;


public interface StudentService {

    StudentDTO getStudent(StudentDTO studentDTO);
    SearchResultDTO<StudentDTO> getAllStudent(StudentDTO studentDTO);
    ResponseStatus addStudent(AddStudentDTO addStudentDTO);
    ResponseStatus editStudent(AddStudentDTO addStudentDTO);
    List<StudentDTO> getListStudentByIdClass(Long id);
    List<StudentDTO>  detailStudentClass(Long id);
}
