package com.example.MiniShop.controller;


import com.example.MiniShop.controller.form.MemberJoinForm;
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
        return "member/memberForm";
    }

    @PostMapping(value = "/member/new")
    public String createMember(MemberJoinForm memberJoinForm) {

        Member member = Member.createMember(new MemberJoinDto(memberJoinForm), passwordEncoder);
        memberService.join(member);

        return "redirect:/";
    }


    @PostMapping(value = "/new")
    public String newMember(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(new MemberJoinDto(memberJoinForm), passwordEncoder);
            memberService.join(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

}