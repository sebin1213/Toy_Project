package com.example.MiniShop.controller;

import com.example.MiniShop.controller.Dto.AdminItemListDto;
import com.example.MiniShop.controller.Dto.ItemCreateDto;
import com.example.MiniShop.controller.Dto.ItemListDto;
import com.example.MiniShop.controller.Dto.ItemUpdateDto;
import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.controller.form.ItemUpdateForm;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import com.example.MiniShop.service.ItemImgService;
import com.example.MiniShop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemImgService itemImgService;

    @GetMapping(value = "/admin/item/new")
    public String createItemForm(Model model){
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
        return "item/createItem";
    }

    @GetMapping(value = "/admin/item")
    public String ItemListAdminForm(Model model){
        List<Item> items = itemService.findAllItem();
        List<AdminItemListDto> collects = items.stream().map(item -> new AdminItemListDto(item.getId(),item.getName(),item.getPrice(),item.getStockQuantity(),item.getItemDetail())).collect(Collectors.toList());
        model.addAttribute("items", collects);
        return "admin/itemList";
    }

    @GetMapping(value = "/admin/item/{itemId}/edit")
    public String ItemEditAdminForm(@PathVariable("itemId") Long itemId, Model model){
        Optional<Item> item_op = itemService.findById(itemId);
        if(item_op.isPresent()){
            Item item = item_op.get();
            model.addAttribute("itemForm", item);
            return "admin/updateItem";
        }
        return "redirect:/admin/item";
    }
    @PostMapping(value = "/admin/item/{itemId}/edit")
    public String ItemUpdateAdmin(@ModelAttribute("itemForm") ItemUpdateForm itemUpdateForm){
        itemService.updateItem(new ItemUpdateDto(itemUpdateForm));
        return "redirect:/admin/item";
    }

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
    @PostMapping(value = "/shop/item/add/cart/{itemId}")
    public String itemDetailCart(@ModelAttribute("itemForm") ItemUpdateForm itemUpdateForm){

        itemService.updateItem(new ItemUpdateDto(itemUpdateForm));
        return "/shop/cart";
    }

}
