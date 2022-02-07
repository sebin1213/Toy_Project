package com.example.MiniShop.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ItemForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String itemDetail;

}