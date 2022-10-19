package com.example.JWTSecure.controller;
import com.example.JWTSecure.DTO.AddTeacherDTO;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.DTO.TeacherDTO;

import com.example.JWTSecure.domain.Activity;
import com.example.JWTSecure.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherResource {

    private final TeacherService teacherService;

    @PutMapping("/edit_profile")
    public ResponseEntity<ResponseStatus> editTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.editTeacher(addTeacherDTO));
    }

    @GetMapping("/view_profile")
    public ResponseEntity<TeacherDTO> getTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok().body(teacherService.viewProfile(teacherDTO));
    }

    @PostMapping("/create_activity")
    public ResponseEntity<ResponseStatus> createAct(@RequestBody Activity activity) {
        return ResponseEntity.ok().body(teacherService.createAct(activity));
    }

//    @GetMapping("/view_activity")
//    public ResponseEntity<TeacherDTO> getActivity() {
//        return ResponseEntity.ok().body(teacherService.getActivity());
//    }
}
