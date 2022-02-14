package com.example.MiniShop.service;

import com.example.MiniShop.controller.MemberJoinDto;
import com.example.MiniShop.controller.form.MemberJoinForm;
import com.example.MiniShop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @Rollback(value = false)
    void joinTest(){
        MemberJoinForm memberJoinForm = new MemberJoinForm();

        memberJoinForm.setUserid("se");
        memberJoinForm.setEmail("test@email.com");
        memberJoinForm.setUsername("sebin");
        memberJoinForm.setPassword("aaaaawww");

        Member member = Member.createMember(new MemberJoinDto(memberJoinForm),passwordEncoder);
        Member savedMember = memberService.join(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getUsername(), savedMember.getUsername());
        assertEquals(member.getUserid(), savedMember.getUserid());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getMemberStatus(), savedMember.getMemberStatus());
    }

    @Test
    public void saveDuplicateMemberTest(){
        MemberJoinForm memberJoinForm = new MemberJoinForm();

        memberJoinForm.setUserid("se");
        memberJoinForm.setEmail("test@email.com");
        memberJoinForm.setUsername("sebin");
        memberJoinForm.setPassword("aaaaawww");
        Member member1 = Member.createMember(new MemberJoinDto(memberJoinForm),passwordEncoder);
        Member member2 = Member.createMember(new MemberJoinDto(memberJoinForm),passwordEncoder);

        memberService.join(member1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);});
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}