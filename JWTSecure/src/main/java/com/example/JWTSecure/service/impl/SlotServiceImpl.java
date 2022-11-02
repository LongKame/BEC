package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.SlotDTO;
import com.example.JWTSecure.domain.Slot;
import com.example.JWTSecure.mapper.SlotMapper;
import com.example.JWTSecure.repo.SlotRepo;
import com.example.JWTSecure.service.SlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService {
    private SlotRepo slotRepo;

    @Override
    public SlotDTO save(SlotDTO slot) {
        return SlotMapper.toDto(slotRepo.save(SlotMapper.toEntity(slot)));
    }

    @Override
    public List<SlotDTO> getSlot(Long clasId) {
        return slotRepo.findByClassId(clasId).stream().map(SlotMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SlotDTO deleteSlot(Long slotId) {
        Optional<Slot> optional = slotRepo.findById(slotId);
        return optional.map(slot -> {
            slotRepo.delete(slot);
            return SlotMapper.toDto(slot);
        }).orElse(null);
    }
}
