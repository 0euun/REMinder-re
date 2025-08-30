package com.example.reminder.controller;

import com.example.reminder.dto.DreamRequestDTO;
import com.example.reminder.dto.DreamResponseDTO;
import com.example.reminder.service.DreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dreams")
public class DreamController {
    private final DreamService dreamService;

    @PostMapping
    public void createDream(@RequestBody DreamRequestDTO dreamRequestDTO) {
        dreamService.createDream(dreamRequestDTO);
    }

    @GetMapping("/{dreamId}")
    public ResponseEntity<DreamResponseDTO> getDream(@PathVariable Long dreamId) {
        return ResponseEntity.ok().body(dreamService.getDream(dreamId));
    }
}
