package com.example.MiniShop.domain;

import com.example.MiniShop.domain.Enum.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 일대다관계라면 다에 외래키 존재
    private Member member;

    private String delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    //==연관관계 편의 메서드==//
    public void setMember(Member member) { //양방향으로 연결
        Order order = new Order();
        member.getOrders().add(order);
        order.setMember(member);
    }
    public void addOrderItem(OrderItem orderItem) { //order랑 orderitem도 양방향
        Order order = new Order();
        orderItems.add(orderItem);
        orderItem.setOrder(order);
    }
    //==생성 메서드==//
    public static Order createOrder(Member member, String delivery,OrderItem... orderItems) { //OrderItem... 개수를 정하지않았을때
        Order order = new Order();
        order.member = member;
        order.delivery = delivery;
        for (OrderItem orderItem : orderItems) {   // orderItems에 있는걸 for로 돌림
            order.addOrderItem(orderItem); // orderItems 에 존재하는 물품 모두 추가
        }
        order.orderStatus = OrderStatus.ORDER; // 상태
        order.orderTime = LocalDateTime.now();  // 현재시각
        return order;
    }
}
