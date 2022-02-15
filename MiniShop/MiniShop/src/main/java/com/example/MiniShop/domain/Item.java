package com.example.MiniShop.domain;

import com.example.MiniShop.controller.Dto.ItemCreateDto;
import com.example.MiniShop.controller.Dto.ItemUpdateDto;
import com.example.MiniShop.domain.Enum.ItemStatus;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private long id;

    @NotNull
    @Column(length = 30)
    private String name;

    @NotNull
    private int price;

    private int stockQuantity = 0; // 재고수량

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_img_id")
    private ItemImg itemImg;

    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus; //상품 판매 상태

    public static Item createItem(ItemCreateDto itemCreateDto){
        Item item = new Item();
        item.name= itemCreateDto.getName();
        item.price=itemCreateDto.getPrice();
        item.stockQuantity=itemCreateDto.getStockQuantity();
        item.itemDetail=itemCreateDto.getItemDetail();
        item.itemImg=itemCreateDto.getItemImg();
        return item;
    }
    public void updateItem(ItemUpdateDto itemUpdateDto){
        this.name= itemUpdateDto.getName();
        this.price=itemUpdateDto.getPrice();
        this.stockQuantity=itemUpdateDto.getStockQuantity();
        this.itemDetail=itemUpdateDto.getItemDetail();
    }



    /***비즈니스 로직****/

    /**재고 추가**/

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**재고 감소**/
    public void removeStock(int quantity){
        if (this.stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
        }
        else{
            System.out.println("재고없음");
        }
    }

    /***현재 시간**/



}
