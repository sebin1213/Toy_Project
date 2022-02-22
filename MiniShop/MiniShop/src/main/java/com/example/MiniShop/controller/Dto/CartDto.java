package com.example.MiniShop.controller.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDto {
    private Long id;
    private String name;
    private int count;
    private int price;
    private String itemImg;

    public CartDto(){

    }
}
