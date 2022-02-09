package com.example.MiniShop.service;

import com.example.MiniShop.domain.Item;
import com.example.MiniShop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(Item item){
        return itemRepository.save(item);
    }
}
