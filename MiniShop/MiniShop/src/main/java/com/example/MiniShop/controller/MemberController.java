package com.example.MiniShop.controller;


import com.example.MiniShop.controller.form.MemberJoinForm;
import com.example.MiniShop.controller.form.MemberLoginForm;
import com.example.MiniShop.domain.Member;
//import com.example.MiniShop.domain.dto.MemberJoinDto;
import com.example.MiniShop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/member/new")
    public String createMemberForm(Model model) {
        model.addAttribute("memberJoinForm", new MemberJoinForm());
        return "member/createMemberForm";
    }

    @PostMapping(value = "/member/new")
    public String createMember(@Valid MemberJoinForm memberJoinForm, BindingResult result){

        if(result.hasErrors()){
            return "member/createMemberForm";
        }
        Member member = Member.createMember(new MemberJoinDto(memberJoinForm), passwordEncoder);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/member/login")
    public String loginMemberForm(Model model){
        return "/member/loginMemberForm";
    }

    @GetMapping(value = "/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "유효하지 않은 아이디, 비밀번호입니다.");
        return "/member/loginMemberForm";
    }

    @GetMapping(value = "/member/logout")
    public String logout(Model model){
        return "redirect:/";
    }

}