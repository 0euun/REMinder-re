package com.example.reminder.controller;

import com.example.reminder.dto.DreamCommentRequestDTO;
import com.example.reminder.dto.DreamCommentResponseDTO;
import com.example.reminder.service.DreamCommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dreamComments")
public class DreamCommentController {
    private final DreamCommentService dreamCommentService;

    @Operation(summary = "댓글 등록", description = "꿈일기에 댓글을 등록합니다.")
    @PostMapping
    public ResponseEntity<String> createDreamComment(@RequestBody DreamCommentRequestDTO dreamCommentRequestDTO, HttpSession session) {
        String message = dreamCommentService.createDreamComment(dreamCommentRequestDTO, session);
        return ResponseEntity.ok(message);
    }

    @Operation(summary = "댓글 단일 조회", description = "특정 댓글을 조회합니다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<DreamCommentResponseDTO> getDreamComment(@PathVariable Long commentId) {
        return ResponseEntity.ok().body(dreamCommentService.getDreamComment(commentId));
    }

    @Operation(summary = "특정 꿈일기 댓글 전체 조회", description = "특정 꿈일기에 달린 전체 댓글을 조회합니다.")
    @GetMapping("/dream/{dreamId}")
    public ResponseEntity<List<DreamCommentResponseDTO>> getDreamCommentsByDreamId(@PathVariable Long dreamId) {
        return ResponseEntity.ok().body(dreamCommentService.getDreamCommentsByDreamId(dreamId));
    }
}
