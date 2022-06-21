package com.example.MiniShop.service;

import com.example.MiniShop.domain.Order;
import com.example.MiniShop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

//    public Order createOrder(List<OrderItemDto> orderItemDtos){
//
//    }
}
