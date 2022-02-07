package com.example.MiniShop.domain;

import com.example.MiniShop.domain.Enum.ItemStatus;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @NotNull
    private int stockQuantity; // 재고수량

    @Lob //문자열을 길이제한 없이 text로 사용
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus; //상품 판매 상태

    private LocalDateTime registerTime; //등록 시간

    private LocalDateTime updateTime; //수정 시간


    @Builder
    public Item(String name, int price,int stockQuantity,String itemDetail){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDetail = itemDetail;
        this.registerTime = LocalDateTime.now();
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
