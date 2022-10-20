package com.example.JWTSecure.controller;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.domain.Quiz;
import com.example.JWTSecure.domain.Room;
import com.example.JWTSecure.service.AcademicAdminService;
import com.example.JWTSecure.service.RoomService;
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
    private final RoomService roomService;
//    @PostMapping("/add_quiz")
//    public ResponseEntity<ResponseStatus> addTeacher(@RequestBody AddTeacherDTO addTeacherDTO) {
//        return ResponseEntity.ok().body(academicAdminService.addTeacher(addTeacherDTO));
//    }

    @GetMapping("/get_quiz/{id}")
    public ResponseEntity<List<Quiz>> getTeachers(@PathVariable Long id) {
        return ResponseEntity.ok().body(academicAdminService.getQuiz(id));
    }

    @GetMapping("/get_room")
    public ResponseEntity<SearchResultDTO<RoomDTO>> getRooms(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok().body(academicAdminService.getRoom(roomDTO));
    }

    @PostMapping("/add_room")
    public ResponseEntity<ResponseStatus> addRoom(@RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.addRoom(room));
    }

    @PutMapping("/edit_room")
    public ResponseEntity<ResponseStatus> editRoom(@RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.editRoom(room));
    }

    @DeleteMapping("/delete_room")
    public ResponseEntity<ResponseStatus> deleteRoom(@RequestBody Room room) {
        return ResponseEntity.ok().body(roomService.deleteRoom(room));
    }

}
