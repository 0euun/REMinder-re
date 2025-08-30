package com.example.reminder.service;

import com.example.reminder.domain.dream.Dream;
import com.example.reminder.domain.member.Member;
import com.example.reminder.dto.DreamRequestDTO;
import com.example.reminder.dto.DreamResponseDTO;
import com.example.reminder.repository.DreamRepository;
import com.example.reminder.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DreamService {
    private final DreamRepository dreamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createDream(DreamRequestDTO dreamRequestDTO) {
        Member member = memberRepository.findById(1L).orElseThrow();

        Dream dream = Dream.builder()
                .title(dreamRequestDTO.getTitle())
                .content(dreamRequestDTO.getContent())
                .type(dreamRequestDTO.getType())
                .isPublic(dreamRequestDTO.getIsPublic())
                .member(member)
                .build();
        dreamRepository.save(dream);
    }

    @Transactional(readOnly = true)
    public DreamResponseDTO getDream(Long id) {
        Dream dream = dreamRepository.findById(id).orElseThrow(() -> new RuntimeException("Dream not found"));
        return DreamResponseDTO.from(dream);
    }
}
