package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.domain.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminItemListDto {
    private Long id;
    private String name;
    private int price;
    private int StockQuantity;
    private String itemDetail;

    public AdminItemListDto(Long id, String name, int price, int StockQuantity, String itemDetail){
        this.id = id;
        this.name = name;
        this.price = price;
        this.StockQuantity = StockQuantity;
        this.itemDetail = itemDetail;
    }
}
