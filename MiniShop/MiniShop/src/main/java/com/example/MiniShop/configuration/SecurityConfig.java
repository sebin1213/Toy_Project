package com.example.MiniShop.configuration;

import com.example.MiniShop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 웹보안 활성화를 위한 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
//                .authorizeRequests() //인가 요청이 오면
//                .antMatchers("/admin").access("hasRole('ADMIN')") //해당 경로들은
////                .permitAll() //접근을 허용한다.
//                .anyRequest() //다른 모든 요청은
//                .authenticated() //인증이 되야 들어갈 수 있다.
//                .and() // 그리고
                    .formLogin()
                    .loginPage("/shop/member/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("userid")
                    .failureUrl("/shop/member/login/error")
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/shop/member/logout"))
                    .logoutSuccessUrl("/")
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){  // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}