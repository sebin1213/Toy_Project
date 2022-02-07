package com.example.MiniShop.domain;

import com.example.MiniShop.controller.MemberJoinDto;
import com.example.MiniShop.domain.Enum.MemberStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter
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

    public static Member createMember(MemberJoinDto memberJoinDto, PasswordEncoder passwordEncoder) {
        Member member =  new Member();
        member.setUserid(memberJoinDto.getUserid());
        String password = passwordEncoder.encode(memberJoinDto.getPassword());
        member.setPassword(password);
        member.setUsername(memberJoinDto.getUsername());
        member.setEmail(memberJoinDto.getEmail());
        member.setMemberStatus(MemberStatus.USER);
        return member;
    }

}
