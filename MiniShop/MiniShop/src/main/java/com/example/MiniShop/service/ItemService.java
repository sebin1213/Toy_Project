package com.example.MiniShop.service;

import com.example.MiniShop.controller.Dto.ItemUpdateDto;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(Item item){
        return itemRepository.save(item);
    }
    public List<Item> findAllItem(){
        return itemRepository.findAll();
    }
    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }
    public void updateItem(ItemUpdateDto itemUpdateDto){
        Optional<Item> itemOptional = itemRepository.findById(itemUpdateDto.getId());
        if (itemOptional.isPresent()){
            Item item = itemOptional.get();
            item.updateItem(itemUpdateDto);
        }
    }
}
