package com.vng.vngtripmap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.vngtripmap.domain.Member;
import com.vng.vngtripmap.dto.LoginDto;
import com.vng.vngtripmap.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public void saveMember(LoginDto loginDto){
        String username = loginDto.getId();
        //아이디 중복검사
        duplicatedIdCheck(username);
        String password = loginDto.getPw();
        //비밀번호 암호화
        password=passwordEncoder.encode(password);

        Member member = new Member(username, password);
        memberRepository.save(member);
    }
    public void duplicatedIdCheck(String id){
        if(memberRepository.existsByUsername(id)){
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }
    }
}
