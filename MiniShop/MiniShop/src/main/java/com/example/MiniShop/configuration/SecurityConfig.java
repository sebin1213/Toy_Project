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
@EnableWebSecurity // 웹보안 활성화를 위한 어노테이션  Spring Security Filter Chain 을 활성화 합니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                    .formLogin()
                    .loginPage("/shop/member/login")
                    .defaultSuccessUrl("/shop")
                    .usernameParameter("userid")
                    .failureUrl("/shop/member/login/error")
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/shop/member/logout"))
                    .logoutSuccessUrl("/")
                .and()
                    .authorizeRequests() // 각 경로 path 별 권한 처리 , 요청에 대한 권한 지정. Security 처리에 HttpServletRequest를 이용한다는 것을 의미한다.
                    .antMatchers("/admin/**").hasRole("ADMIN")// 해당 경로로 접근할시 인증을 요청 ( 구체적인 경로가 먼저와야한다 )
                    .antMatchers("/**").permitAll() // admin을 제외한 나머지 경로는 어떤 사용자든지 접근할 수 있습니다.
                .and()
                    .httpBasic() // 로그인 인증창이 뜨게함
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