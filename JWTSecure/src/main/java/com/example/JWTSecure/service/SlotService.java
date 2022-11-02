package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.SlotDTO;

import java.util.List;

public interface SlotService {

    SlotDTO save(SlotDTO slot);

    List<SlotDTO> getSlot(Long clasId);

    SlotDTO deleteSlot(Long slotId);
}
