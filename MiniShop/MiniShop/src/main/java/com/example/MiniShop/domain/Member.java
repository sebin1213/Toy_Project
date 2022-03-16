package com.example.MiniShop.domain;

import com.example.MiniShop.controller.Dto.MemberJoinDto;
import com.example.MiniShop.domain.Enum.MemberStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 30)
    private String userid;

    @JsonIgnore
    private String password;

    @Column(length = 50)
    private String username;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.USER;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    private String address;

    public static Member createMember(MemberJoinDto memberJoinDto, Cart cart,PasswordEncoder passwordEncoder) {
        Member member =  new Member();

        member.userid = memberJoinDto.getUserid();
        String password = passwordEncoder.encode(memberJoinDto.getPassword());
        member.password = password;
        member.username = memberJoinDto.getUsername();
        member.email = memberJoinDto.getEmail();
        member.memberStatus = MemberStatus.USER;
        member.cart = cart;
        return member;
    }

}
