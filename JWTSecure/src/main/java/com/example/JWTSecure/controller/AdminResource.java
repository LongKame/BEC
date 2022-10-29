package com.example.JWTSecure.controller;
import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.domain.Teacher;
import com.example.JWTSecure.service.AcademicAdminService;
import com.example.JWTSecure.service.ClassService;
import com.example.JWTSecure.service.StudentService;
import com.example.JWTSecure.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminResource {

    private final TeacherService teacherService;
    private final AcademicAdminService academicAdminService;
    private final ClassService classService;
    private final StudentService studentService;

    @GetMapping("/getall")
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok().body(teacherService.list());
    }

    @PostMapping("/add_teacher")
    public ResponseEntity<ResponseStatus> addTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.addTeacher(addTeacherDTO));
    }

    @PostMapping("/view_teacher")
    public ResponseEntity<SearchResultDTO<TeacherDTO>> getTeachers(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok().body(teacherService.getAllTeacher(teacherDTO));
    }

    @PutMapping("/delete_teacher")
    public ResponseEntity<ResponseStatus> deleteTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.deleteTeacher(addTeacherDTO.getId()));
    }

    @PutMapping("/edit_teacher")
    public ResponseEntity<ResponseStatus> editTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
        return ResponseEntity.ok().body(teacherService.editTeacher(addTeacherDTO));
    }

    @PostMapping("/add_acad")
    public ResponseEntity<ResponseStatus> addAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.addAcad(addAcademicAdminDTO));
    }

    @PostMapping("/view_acad")
    public ResponseEntity<SearchResultDTO<AcademicAdminDTO>> getAcad(@RequestBody AcademicAdminDTO academicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.getAllAcad(academicAdminDTO));
    }

    @PutMapping("/edit_acad")
    public ResponseEntity<ResponseStatus> editAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.editAcad(addAcademicAdminDTO));
    }

    @PutMapping("/delete_acad")
    public ResponseEntity<ResponseStatus> deleteAcad(@RequestBody AddAcademicAdminDTO addAcademicAdminDTO) {
        return ResponseEntity.ok().body(academicAdminService.deleteAcad(addAcademicAdminDTO.getId()));
    }

    @PostMapping("/get_all_class")
    public ResponseEntity<SearchResultDTO<ClassDTO>> getClasses(@RequestBody ClassDTO classDTO) {
        return ResponseEntity.ok().body(classService.getAllClass(classDTO));
    }

    @PostMapping("/view_student_class/{id}")
    public ResponseEntity<List<StudentDTO>> getListStudentClass(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.getListStudentByIdClass(id));
    }

    @PostMapping("/search_student")
    public ResponseEntity<SearchResultDTO<StudentDTO>> searchStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.getAllStudent(studentDTO));
    }

    @PostMapping("/detail_student_class/{id}")
    public ResponseEntity<List<StudentDTO>> detailStudentClass(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.detailStudentClass(id));
    }
}
