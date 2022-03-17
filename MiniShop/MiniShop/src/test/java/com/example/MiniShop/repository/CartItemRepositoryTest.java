package com.example.MiniShop.repository;

import com.example.MiniShop.domain.CartItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartItemRepositoryTest {
    @Autowired
    CartItemRepository cartItemRepository;


}