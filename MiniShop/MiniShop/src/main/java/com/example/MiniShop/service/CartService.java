package com.example.MiniShop.service;

import com.example.MiniShop.domain.CartItem;
import com.example.MiniShop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public CartItem addCart(CartItem cartItem){
        return cartRepository.save(cartItem);
    }
}
