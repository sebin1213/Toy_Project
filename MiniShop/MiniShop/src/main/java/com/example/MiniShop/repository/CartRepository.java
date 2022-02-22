package com.example.MiniShop.repository;

import com.example.MiniShop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
}
