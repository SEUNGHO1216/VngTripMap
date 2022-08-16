package com.vng.vngtripmap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //config어노테이션이 이미 있으니 이 클래스가 스프링 컨테이너에 올라갈때 같이 올라감
    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //h2 console에 대한 csrf 무시/ 허용
        web.ignoring()
                .antMatchers("/h2-console/**","/member/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //회원 관리 API(POST 요청시 시큐리티가 자동적으로 csrf 토큰을 확인), 이 부분을 ignore
        http.csrf().disable();
//                .ignoringAntMatchers("/member/signup", "/member/login");
        http.authorizeRequests()
                //""안의 내용들은 인증 과정 없이 사용 가능
                .antMatchers("/images/**","/css/**","/","/member/signup","/member/login","/user/login")
                .permitAll()
                // 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                // 로그인 기능 허용
                .formLogin()
                //인증을 위한 로그인 페이지로 이동(GET)
                .loginPage("/member/login")
                //로그인 페이지에서 실질적인 로그인 과정이 일어나는 api(POST)
                .loginProcessingUrl("/member/login.do")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login?error")
                .permitAll()
                .and()
                // 로그아웃 기능 허용
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }

}
