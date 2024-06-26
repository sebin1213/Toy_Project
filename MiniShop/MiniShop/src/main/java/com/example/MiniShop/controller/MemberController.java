package com.example.MiniShop.controller;


import com.example.MiniShop.controller.Dto.MemberJoinDto;
import com.example.MiniShop.controller.Dto.MyPageMemberDto;
import com.example.MiniShop.controller.form.MemberJoinForm;
import com.example.MiniShop.domain.Cart;
import com.example.MiniShop.domain.Member;
//import com.example.MiniShop.domain.dto.MemberJoinDto;
import com.example.MiniShop.service.CartService;
import com.example.MiniShop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/shop/member/new")
    public String createMemberForm(Model model) {
        model.addAttribute("memberJoinForm", new MemberJoinForm());
        return "member/createMemberForm";
    }

    @PostMapping(value = "/shop/member/new")
    public String createMember(@Valid MemberJoinForm memberJoinForm, BindingResult result){
        if(result.hasErrors()){
            return "member/createMemberForm";
        }
        Cart cart = cartService.createCart(new Cart());
        Member member = Member.createMember(new MemberJoinDto(memberJoinForm),cart,passwordEncoder);
        memberService.join(member);

        return "redirect:/shop";
    }

    @GetMapping(value = "/shop/member/login")
    public String loginMemberForm(Model model){
        return "/member/loginMemberForm";
    }

    //json형식으로 데이터를 보내 검증과정 거침...
    @GetMapping(value = "/shop/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "유효하지 않은 아이디, 비밀번호입니다.");
        return "/member/loginMemberForm";
    }

    @GetMapping(value = "/shop/member/logout")
    public String logout(Model model){
        return "redirect:/shop";
    }

    @GetMapping(value = "/shop/mypage")
    public String MyPageForm(Model model, Principal principal){
        Member member = memberService.findByUserId(principal.getName());
        MyPageMemberDto myPageMemberDto= new MyPageMemberDto(member);
        model.addAttribute("member",myPageMemberDto);
        return "/member/myPageForm";
    }

}