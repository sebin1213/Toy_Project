package com.example.MiniShop.controller.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDto {
    private Long itemId;
    private String name;
    private int count;
    private int price;
    private String itemImgUrl;

    public CartItemDto(Long itemId, String name, int count, int price, String itemImgUrl){
        this.name = name;
        this.count = count;
        this.price = price;
        this.itemId = itemId;
        this.itemImgUrl = itemImgUrl;
    }
}
