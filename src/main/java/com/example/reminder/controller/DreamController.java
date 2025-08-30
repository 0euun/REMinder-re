package com.example.reminder.controller;

import com.example.reminder.dto.DreamRequestDTO;
import com.example.reminder.dto.DreamResponseDTO;
import com.example.reminder.service.DreamService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dreams")
public class DreamController {
    private final DreamService dreamService;

    @Operation(summary = "꿈일기 작성", description = "꿈일기를 작성합니다.")
    @PostMapping
    public void createDream(@RequestBody DreamRequestDTO dreamRequestDTO) {
        dreamService.createDream(dreamRequestDTO);
    }

    @Operation(summary = "특정 꿈일기 조회", description = "특정 꿈일기를 조회합니다.")
    @GetMapping("/{dreamId}")
    public ResponseEntity<DreamResponseDTO> getDream(@PathVariable Long dreamId) {
        return ResponseEntity.ok().body(dreamService.getDream(dreamId));
    }

    @Operation(summary = "꿈일기 수정", description = "꿈일기를 수정합니다.")
    @PutMapping("/{dreamId}")
    public ResponseEntity<DreamResponseDTO> updateDream(@PathVariable Long dreamId, @RequestBody DreamRequestDTO dreamRequestDTO) {
        dreamService.updateDream(dreamId, dreamRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "꿈일기 삭제", description = "꿈일기를 삭제합니다.")
    @DeleteMapping("/{dreamId}")
    public ResponseEntity<Void> deleteDream(@PathVariable Long dreamId) {
        dreamService.deleteDream(dreamId);
        return ResponseEntity.ok().build();
    }
}
