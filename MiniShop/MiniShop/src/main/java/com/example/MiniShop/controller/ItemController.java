package com.example.MiniShop.controller;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.controller.form.ItemImgForm;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import com.example.MiniShop.repository.ItemRepository;
import com.example.MiniShop.service.ItemImgService;
import com.example.MiniShop.service.ItemService;
import com.example.MiniShop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemImgService itemImgService;

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("errorMessageImg", "");
        model.addAttribute("itemForm", new ItemForm());
        return "item/createItem";
    }

    @PostMapping(value = "/admin/item/new")
    public String createItem(@RequestParam("itemImgFileList")MultipartFile itemImgFileList,@Valid ItemForm itemForm, BindingResult result, Model model) throws Exception{
        if(result.hasErrors()){
            return "item/createItem";
        }
        if(itemImgFileList.isEmpty()){
            model.addAttribute("errorMessageImg", "상품 이미지는 필수 입력 값 입니다.");
            return "item/createItem";
        }

        ItemImg itemImg = itemImgService.createImg(itemImgFileList);

        itemService.create(Item.createItem(new ItemCreateDto(itemForm,itemImg)));
//        itemService.create(Item.createItem(itemForm.getName(),itemForm.getPrice(),itemForm.getStockQuantity(),itemForm.getItemDetail(),itemImg));
        return "item/createItem";
    }

    @GetMapping(value = "/shop/item/top")
    public String itemListForm(Model model){
        List<Item> items = itemService.findAllItem();
        List<ItemListDto> collects = items.stream().map(item -> new ItemListDto(item.getName(),item.getPrice(),item.getItemDetail(),
                item.getItemImg().getImgUrl())).collect(Collectors.toList());
        model.addAttribute("items", collects);
        return "item/topList";
    }
}
