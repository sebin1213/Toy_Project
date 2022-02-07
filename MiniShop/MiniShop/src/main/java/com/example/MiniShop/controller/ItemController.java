package com.example.MiniShop.controller;

import com.example.MiniShop.controller.form.ItemForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model) {
        ItemForm itemform = new ItemForm();
        itemform.setName("테스트 상품1");
        itemform.setPrice(10000);
        itemform.setStockQuantity(10);
        itemform.setItemDetail("상품 상세 설명");

        model.addAttribute("itemForm", itemform);
        return "items/createItem";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){

        List<ItemForm> itemFormList = new ArrayList<>();

        for(int i=1;i<=10;i++){

            ItemForm itemform = new ItemForm();
            itemform.setName("테스트 상품1"+i);
            itemform.setPrice(10000+i);
            itemform.setStockQuantity(10+i);
            itemform.setItemDetail("상품 상세 설명"+i);

            itemFormList.add(itemform);
        }

        model.addAttribute("itemFormList", itemFormList);
        return "items/thymeleafEx04";
    }
}
