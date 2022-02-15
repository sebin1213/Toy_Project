package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.domain.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemCreateDto {
    private String name;
    private int price;
    private int StockQuantity;
    private String itemDetail;
    private ItemImg itemImg;

    public ItemCreateDto(ItemForm itemForm, ItemImg itemImg){
        this.name = itemForm.getName();
        this.price = itemForm.getPrice();
        this.StockQuantity = itemForm.getStockQuantity();
        this.itemDetail = itemForm.getItemDetail();
        this.itemImg = itemImg;
    }
}
