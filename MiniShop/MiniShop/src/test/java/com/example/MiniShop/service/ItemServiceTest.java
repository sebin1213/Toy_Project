package com.example.MiniShop.service;

import com.example.MiniShop.controller.ItemCreateDto;
import com.example.MiniShop.controller.ItemListDto;
import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Test
    Item createItemTest(){
        ItemImg itemImg = new ItemImg();
        Item item = new Item();
        ItemForm itemForm = new ItemForm();

        itemForm.setName("imgname");
        itemForm.setItemDetail("itemDetail");
        itemForm.setPrice(20000);
        ItemImg c_itemImg = itemImg.createItemImg("imgname","orgname","imgurl");

        ItemCreateDto itemCreateDto = new ItemCreateDto(itemForm,c_itemImg);

        Item save_item = item.createItem(itemCreateDto);
        return save_item;
    }
    @Test
    void allItem(){
        itemService.create(createItemTest());
        List<Item> items = itemService.findAllItem();

        for(Item item:items){
            assertEquals("imgurl", item.getItemImg().getImgUrl());
        }
    }

    @Test
    @Rollback(value = false)
    void controllerTest(){
        itemService.create(createItemTest());
        List<Item> items = itemService.findAllItem();
        List<ItemListDto> collects = items.stream().map(item -> new ItemListDto(item.getName(),item.getPrice(),item.getItemDetail(),
                item.getItemImg().getImgUrl())).collect(Collectors.toList());
        assertEquals(1, collects.size());
        for(ItemListDto item : collects){
            assertEquals("imgurl", item.getImgUrl());
            break;
        }

    }

}
