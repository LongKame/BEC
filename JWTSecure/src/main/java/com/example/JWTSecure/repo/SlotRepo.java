package com.example.JWTSecure.repo;

import com.example.JWTSecure.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepo extends JpaRepository<Slot, Long> {
    List<Slot> findByClassId(Long classId);
}
