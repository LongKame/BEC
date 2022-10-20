package com.example.JWTSecure.repo;


import com.example.JWTSecure.domain.Role;
import com.example.JWTSecure.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    Room findByRoomname(String roomname);
//    Room findById(Long id);
}