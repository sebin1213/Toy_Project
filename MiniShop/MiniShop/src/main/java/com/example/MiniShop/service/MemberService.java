package com.example.MiniShop.service;

import com.example.MiniShop.domain.Member;
import com.example.MiniShop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member join(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserid(member.getUserid());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    /****로그인 기능 (UserDetails 오버라이드)****/
    @Override
    public UserDetails loadUserByUsername(String userid) throws
            UsernameNotFoundException {
        Member member = memberRepository.findByUserid(userid);

        if(member == null) {
            throw new UsernameNotFoundException(userid);
        }
        return User.builder()
                .username(member.getUserid())
                .password(member.getPassword())
                .roles(member.getMemberStatus().toString())
                .build();
    }

}