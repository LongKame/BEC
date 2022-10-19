package com.example.JWTSecure.controller;
import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    @PostMapping("/add_student")
    public ResponseEntity<ResponseStatus> addStudent(@RequestBody AddStudentDTO addStudentDTO) {
        return ResponseEntity.ok().body(studentService.addStudent(addStudentDTO));
    }

    @GetMapping("/view_student")
    public ResponseEntity<StudentDTO> getStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.getStudent(studentDTO));
    }

    @GetMapping("/view_students")
    public ResponseEntity<SearchResultDTO<StudentDTO>> getAllStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.getAllStudent(studentDTO));
    }

    @PutMapping("/edit_student")
    public ResponseEntity<ResponseStatus> editStudent(@RequestBody AddStudentDTO addStudentDTO) {
        return ResponseEntity.ok().body(studentService.editStudent(addStudentDTO));
    }
}
