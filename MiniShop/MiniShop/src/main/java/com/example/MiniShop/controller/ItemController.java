package com.example.MiniShop.controller;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.repository.ItemRepository;
import com.example.MiniShop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemForm", new ItemForm());
        return "item/createItem";
    }
    @PostMapping(value = "/admin/item/new")
    public String createItem(@Valid ItemForm itemForm, BindingResult result){
        if(result.hasErrors()){
            return "item/createItem";
        }
        Item item = Item.createItem(itemForm.getName(),itemForm.getPrice(),itemForm.getStockQuantity(),itemForm.getItemDetail());
        itemService.create(item);
        return "item/createItem";
    }
}
