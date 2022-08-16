package com.vng.vngtripmap.security;

import com.vng.vngtripmap.domain.Member;
import com.vng.vngtripmap.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //앞단의 Authentication manager가 현재 UserdetailsService에 username을 가지고
    //db를 조회하여 일치하는 회원이 있는지 찾아달라고 요청
    //=> 있으면 비밀번호까지 정보를 담은 유저 객체(userDetails) 반환/ 없으면 에러 반환
    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(member);
    }
}