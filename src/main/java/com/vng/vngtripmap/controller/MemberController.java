package com.vng.vngtripmap.controller;

import com.vng.vngtripmap.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    //메인 페이지(로그인 정보에 따라 동적으로 nav bar 변환)
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails!=null){
            model.addAttribute("userInfo", userDetails.getUsername());
        }
        return "index";
    }
    //회원가입 페이지 이동
    @GetMapping("/member/signup")
    public String register(){
        return "signup";
    }
    //로그인 페이지 이동
    @GetMapping("/member/login")
    public String login(){
        return "login";
    }
//

}
