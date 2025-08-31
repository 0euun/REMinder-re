package com.example.reminder.service;

import com.example.reminder.domain.member.Member;
import com.example.reminder.dto.LoginRequestDTO;
import com.example.reminder.dto.LoginResponseDTO;
import com.example.reminder.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, HttpSession session) {
        Member member = memberRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));
        if(!member.getPassword().equals(loginRequestDTO.getPassword())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        session.setAttribute("memberId", member.getId());

        return LoginResponseDTO.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .profileImage(member.getProfileImage())
                .build();
    }

    @Transactional
    public String logout(HttpSession session) {
        session.invalidate();
        return "로그아웃 되었습니다.";
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO getMe(HttpSession session) {
        Object memberId = session.getAttribute("memberId");
        if(memberId == null){
            throw new RuntimeException("로그인이 필요합니다.");
        }
        Member member = memberRepository.findById((Long) memberId).orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

        return LoginResponseDTO.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .profileImage(member.getProfileImage())
                .build();
    }
}