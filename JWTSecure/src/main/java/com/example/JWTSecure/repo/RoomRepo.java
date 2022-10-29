package com.example.JWTSecure.repo;


import com.example.JWTSecure.domain.Role;
import com.example.JWTSecure.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    Room findByRoomname(String roomname);
    @Modifying
    @Query("UPDATE Room room SET room.active = false WHERE room.id = ?1")
    int deactive(Long id);
}