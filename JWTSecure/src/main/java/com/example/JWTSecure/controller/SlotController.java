package com.example.JWTSecure.controller;

import com.example.JWTSecure.DTO.SlotDTO;
import com.example.JWTSecure.service.SlotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/slot")
@AllArgsConstructor
public class SlotController {
    private SlotService slotService;

    @PostMapping("")
    public SlotDTO createSlot(@RequestBody SlotDTO slot) {
        if (slot.getId() != null) throw new RuntimeException("Slot id must be null");
        return slotService.save(slot);
    }

    @PutMapping("")
    public SlotDTO updateSlot(@RequestBody SlotDTO slot) {
        if (slot.getId() == null) throw new RuntimeException("Slot id must not null");
        return slotService.save(slot);
    }

    @GetMapping("{clasId}")
    public List<SlotDTO> getSlot(@PathVariable Long clasId) {
        return slotService.getSlot(clasId);
    }

    @DeleteMapping("{slotId}")
    public SlotDTO deleteSlot(@PathVariable Long slotId) {
        SlotDTO dto = slotService.deleteSlot(slotId);
        if (dto == null) throw new RuntimeException("Not found slot");
        return dto;
    }

}
