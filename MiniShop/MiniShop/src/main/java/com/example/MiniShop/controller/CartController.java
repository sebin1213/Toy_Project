package com.example.MiniShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class CartController {

    @GetMapping(value = "/shop/cart")
    public String cartForm(Model model){

        return "/member/myPageForm";
    }
}
