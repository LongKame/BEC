package com.example.JWTSecure.controller;

import com.example.JWTSecure.DTO.AddTeacherDTO;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.DTO.TeacherDTO;
import com.example.JWTSecure.domain.Quiz;
import com.example.JWTSecure.service.AcademicAdminService;
import com.example.JWTSecure.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aca")
@RequiredArgsConstructor
public class AcademicAdminResource {

    private final AcademicAdminService academicAdminService;
//    @PostMapping("/add_quiz")
//    public ResponseEntity<ResponseStatus> addTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
//        return ResponseEntity.ok().body(academicAdminService.addTeacher(addTeacherDTO));
//    }

    @GetMapping("/get_quiz/{id}")
    public ResponseEntity<List<Quiz>> getTeachers(@PathVariable Long id) {
        return ResponseEntity.ok().body(academicAdminService.getQuiz(id));
    }

}
