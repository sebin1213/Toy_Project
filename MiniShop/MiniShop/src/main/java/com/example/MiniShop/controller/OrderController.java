package com.example.MiniShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping(value = "/shop/order")
    public void OrderItem(Model model){

    }
}
