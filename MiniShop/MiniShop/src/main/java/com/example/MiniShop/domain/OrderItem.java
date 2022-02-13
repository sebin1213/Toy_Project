package com.example.MiniShop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    private LocalDateTime orderTime;

    protected OrderItem(){
        // 외부에서 로직을 건들지 못하게 하기위해
    }
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) { // Item item 필수로 들어가야함... 왜..?
        // int orderPrice 아이템에 가격이 있는데 이걸 안쓰는 이유는 뭐.. 할인될수도 있으니까?
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.orderPrice = orderPrice;
        orderItem.count = count;
        item.removeStock(count); // 넘어온 개수만큼 재고를 줄여야함
        return orderItem;
    }


    /**연관관계 메서드**/
    public void setOrder(Order order){
        OrderItem orderItem = new OrderItem();
        order.getOrderItems().add(orderItem);
        orderItem.setOrder(order);
    }

}
