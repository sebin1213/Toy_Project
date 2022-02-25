package com.example.MiniShop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Cart {
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval=true) //orphanRemoval=true 고아 객체를 삭제해준다. 장바구니까 날림... 상품이면 날리면 안됨.....
    private List<CartItem> cartItems = new ArrayList<>(); // 왜 ArrayList?? 1:N 관계에서 N이 없는 경우 null인 상태인 보다 Empty가 훨씬 직관적이다.null의 경우 값을 못 가져온 것인지 값이 비어있는건지 애매하다.
    /**
    / 연관관계 메서드
     **/
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

}
