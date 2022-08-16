package com.vng.vngtripmap.controller;

import com.vng.vngtripmap.domain.Member;
import com.vng.vngtripmap.dto.LoginDto;
import com.vng.vngtripmap.repository.MemberRepository;
import com.vng.vngtripmap.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberRestController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/member/signup")
    public void register(@RequestBody LoginDto loginDto){
        memberService.saveMember(loginDto);
    }
}
