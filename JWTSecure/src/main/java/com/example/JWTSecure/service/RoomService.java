package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.AddStudentDTO;
import com.example.JWTSecure.DTO.ResponseStatus;
import com.example.JWTSecure.DTO.RoomDTO;
import com.example.JWTSecure.domain.Room;

public interface RoomService {

    ResponseStatus addRoom(Room room);
    ResponseStatus editRoom(Room room);
    ResponseStatus deleteRoom(Room room);
}
