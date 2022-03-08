package com.example.MiniShop.controller;

import com.example.MiniShop.controller.Dto.CartItemDto;
import com.example.MiniShop.domain.CartItem;
import com.example.MiniShop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(value = "/shop/cart")
    public String cartForm(Model model,Principal principal){

        List<CartItem> cartItems = cartService.cartItemList(principal.getName());


        List<CartItemDto> cartItemDtoList = cartItems.stream().map(cartItem -> new CartItemDto(cartItem.getItem().getId(),cartItem.getItem().getName(),cartItem.getCount(),
                cartItem.getItem().getPrice(),cartItem.getItem().getItemImg().getImgUrl())).collect(Collectors.toList());

        model.addAttribute("itemList",cartItemDtoList);
//        cartService.cartItemList(principal.getName());

        return "/cart/cartForm";
    }

    @GetMapping(value = "/shop/cart/{item_id}/delete")
    public String cartDelete(Model model, @PathVariable("item_id") Long item_id,Principal principal){
        cartService.deleteCart(item_id,principal.getName());
        return "redirect:/shop/cart";
    }
}
