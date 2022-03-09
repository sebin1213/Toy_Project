package com.example.MiniShop.repository;

import com.example.MiniShop.domain.Cart;
import com.example.MiniShop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    @Override
    void deleteById(Long aLong);
}
