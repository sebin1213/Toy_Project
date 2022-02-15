package com.example.MiniShop.controller.Dto;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.controller.form.ItemUpdateForm;
import com.example.MiniShop.domain.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateDto {
    private Long id;
    private String name;
    private int price;
    private int StockQuantity;
    private String itemDetail;
    private ItemImg itemImg;

    public ItemUpdateDto(ItemUpdateForm itemUpdateForm){
        this.id = itemUpdateForm.getId();
        this.name = itemUpdateForm.getName();
        this.price = itemUpdateForm.getPrice();
        this.StockQuantity = itemUpdateForm.getStockQuantity();
        this.itemDetail = itemUpdateForm.getItemDetail();
//        this.itemImg = itemImg;
    }
}