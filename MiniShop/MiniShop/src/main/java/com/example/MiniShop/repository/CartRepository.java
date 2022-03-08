package com.example.MiniShop.repository;

import com.example.MiniShop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Override
    void deleteById(Long aLong);
}
