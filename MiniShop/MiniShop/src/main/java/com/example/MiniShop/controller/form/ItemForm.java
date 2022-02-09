package com.example.MiniShop.controller.form;

import com.example.MiniShop.domain.ItemImg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemForm {
    @NotEmpty(message = "상품 이름은 필수 입력 값입니다")
    private String name;

    @NotNull(message = "상품가격은 필수 입력 값입니다")
    private int price;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private int stockQuantity;

    private ItemImg itemImg;

    private String itemDetail;

}