package com.example.MiniShop.controller;

import com.example.MiniShop.controller.form.MemberJoinForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinDto {
    private String userid;

    private String password;

    private String username;

    private String email;

    public MemberJoinDto(MemberJoinForm memberJoinForm) {
        this.userid = memberJoinForm.getUserid();
        this.password = memberJoinForm.getPassword();
        this.email = memberJoinForm.getEmail();
        this.username = memberJoinForm.getUsername();
    }
}
