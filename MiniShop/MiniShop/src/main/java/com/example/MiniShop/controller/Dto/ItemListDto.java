package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.domain.ItemImg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemListDto {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수 입력 값입니다")
    private String name;

    @NotNull(message = "상품가격은 필수 입력 값입니다")
    private int price;

    private String imgUrl = "/scr";

    private String itemDetail;

    public ItemListDto(Long id,String name,int price, String itemDetail,String imgUrl){
        this.id = id;
        this.name=name;
        this.itemDetail=itemDetail;
        this.imgUrl = imgUrl;
        this.price=price;
    }

}