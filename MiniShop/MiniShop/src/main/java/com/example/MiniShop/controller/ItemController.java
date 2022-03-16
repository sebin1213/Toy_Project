package com.example.MiniShop.controller;

import com.example.MiniShop.controller.Dto.AdminItemListDto;
import com.example.MiniShop.controller.Dto.ItemCreateDto;
import com.example.MiniShop.controller.Dto.ItemListDto;
import com.example.MiniShop.controller.Dto.ItemUpdateDto;
import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.controller.form.ItemUpdateForm;
import com.example.MiniShop.domain.CartItem;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import com.example.MiniShop.service.CartService;
import com.example.MiniShop.service.ItemImgService;
import com.example.MiniShop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CartService cartService;

    @GetMapping(value = "/shop/item/list")
    public String itemListForm(Model model){
        List<Item> items = itemService.findAllItem();
        List<ItemListDto> collects = items.stream().map(item -> new ItemListDto(item.getId(),item.getName(),item.getPrice(),item.getItemDetail(),
                item.getItemImg().getImgUrl())).collect(Collectors.toList());
        model.addAttribute("items", collects);
        return "item/topList";
    }

    @GetMapping(value = "/shop/item/list/{itemId}")
    public String itemDetailForm(@PathVariable("itemId") Long itemId, Model model){
        Optional<Item> item_op = itemService.findById(itemId);
        if (item_op.isPresent()){
            Item item = item_op.get();
            ItemListDto item_dto = new ItemListDto(item.getId(),item.getName(),item.getPrice(),item.getItemDetail(),
                    item.getItemImg().getImgUrl());
            model.addAttribute("item", item_dto);
            return "item/itemDetail";
        }
        return "item/topList";
    }

    @PostMapping(value = "/shop/item/list/{itemId}")
    public String itemDetailCart(Principal principal, @PathVariable("itemId") Long itemId, @RequestParam int quantity){
        cartService.addCart(principal.getName(),itemId,quantity);

        return "redirect:/shop/item/list/{itemId}";
    }

}
