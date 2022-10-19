package com.example.JWTSecure.controller;
import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.service.AcademicAdminService;
import com.example.JWTSecure.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminResource {

    private final TeacherService teacherService;
    private final AcademicAdminService academicAdminService;

    @PostMapping("/add_teacher")
    public ResponseEntity<ResponseStatus> addTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.addTeacher(addTeacherDTO));
    }

    @GetMapping("/view_teacher")
    public ResponseEntity<SearchResultDTO<TeacherDTO>> getTeachers(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok().body(teacherService.getAllTeacher(teacherDTO));
    }

    @DeleteMapping("/delete_teacher")
    public ResponseEntity<ResponseStatus> deleteTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.deleteTeacher(addTeacherDTO));
    }

    @PutMapping("/edit_teacher")
    public ResponseEntity<ResponseStatus> editTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.editTeacher(addTeacherDTO));
    }

    @PostMapping("/add_acad")
    public ResponseEntity<ResponseStatus> addAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.addAcad(addAcademicAdminDTO));
    }

    @GetMapping("/view_acad")
    public ResponseEntity<SearchResultDTO<AcademicAdminDTO>> getAcad(@RequestBody AcademicAdminDTO academicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.getAllAcad(academicAdminDTO));
    }

    @PutMapping("/edit_acad")
    public ResponseEntity<ResponseStatus> editAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.editAcad(addAcademicAdminDTO));
    }

    @DeleteMapping("/delete_acad")
    public ResponseEntity<ResponseStatus> deleteAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.deleteAcad(addAcademicAdminDTO));
    }
}
