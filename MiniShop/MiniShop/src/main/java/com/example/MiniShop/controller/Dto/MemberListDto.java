package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.domain.Cart;
import com.example.MiniShop.domain.Enum.MemberStatus;
import lombok.Getter;

@Getter
public class MemberListDto {
    private Long id;
    private String userid;
    private String password;
    private String username;
    private String email;
    private MemberStatus memberStatus;
    private int orderCount;
    private String address; //기본 주소지

    public MemberListDto(Long id, String userid,String password,String username,String email,MemberStatus memberStatus,int orderCount,String address){
        this.id = id;
        this.userid=userid;
        this.password=password;
        this.username=username;
        this.email=email;
        this.memberStatus=memberStatus;
        this.orderCount=orderCount;
        this.address=address;
    }

}
