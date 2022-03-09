package com.example.MiniShop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
public class CartItem {

    @Id @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    protected CartItem(){
    }

    /**
     * 연관관계 메서드
     * */
    public void setCart(Cart cart){
        this.cart=cart;
    }
    public static CartItem createCartItem(Item item, int count){
        CartItem cartItem = new CartItem();
        cartItem.item = item;
        cartItem.count = count;
        return cartItem;
    }
    public void addCountCartItem(int count){
        this.count +=count;
    }
    public int getTotalPrice() {
        return getItem().getPrice() * getCount();
    }

}