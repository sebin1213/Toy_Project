package com.example.MiniShop.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberJoinForm {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=10, message = "비밀번호는 10자 이상 입력해주세요")
    private String password;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

}