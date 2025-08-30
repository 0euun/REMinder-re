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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DreamService {
    private final DreamRepository dreamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createDream(DreamRequestDTO dreamRequestDTO) {
        Member member = memberRepository.findById(1L).orElseThrow();

        Dream dream = Dream.builder()
                .title(dreamRequestDTO.getTitle())
                .content(dreamRequestDTO.getContent())
                .type(dreamRequestDTO.getType())
                .isPublic(dreamRequestDTO.getIsPublic())
                .member(member)
                .build();
        dreamRepository.save(dream);

        return "꿈일기가 등록되었습니다.";
    }

    @Transactional(readOnly = true)
    public DreamResponseDTO getDream(Long id) {
        Dream dream = dreamRepository.findById(id).orElseThrow(() -> new RuntimeException("Dream not found"));
        return DreamResponseDTO.from(dream);
    }

    @Transactional(readOnly = true)
    public List<DreamResponseDTO> getAllDreams() {
        List<Dream> dreams = dreamRepository.findAll();
        return dreams.stream().map(DreamResponseDTO::from).collect(Collectors.toList());
    }

    @Transactional
    public String updateDream(Long id, DreamRequestDTO dreamRequestDTO) {
        Dream dream = dreamRepository.findById(id).orElseThrow(() -> new RuntimeException("Dream not found"));
        dream.updateFromDTO(dreamRequestDTO);

        return "꿈일기가 수정되었습니다.";
    }

    @Transactional
    public String deleteDream(Long id) {
        Dream dream = dreamRepository.findById(id).orElseThrow(() -> new RuntimeException("Dream not found"));
        dreamRepository.delete(dream);

        return "꿈일기가 삭제되었습니다.";
    }
}
