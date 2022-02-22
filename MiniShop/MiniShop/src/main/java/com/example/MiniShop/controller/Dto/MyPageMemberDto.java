package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.domain.Member;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MyPageMemberDto {
    private String userid;

    private String username;

    private String email;

    private String address;

    public MyPageMemberDto(Member member) {
        this.userid = member.getUserid();
        this.address = member.getAddress();
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}