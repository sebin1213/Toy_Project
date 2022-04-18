package com.example.MiniShop.repository;

import com.example.MiniShop.domain.CartItem;

import java.util.List;

public interface CartRepositoryCustom {
    List<CartItem> findCartItemCustom(String userId);
}
