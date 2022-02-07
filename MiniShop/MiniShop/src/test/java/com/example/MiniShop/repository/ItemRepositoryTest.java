package com.example.MiniShop.repository;

import com.example.MiniShop.domain.Enum.ItemStatus;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void item_save_test() throws Exception{
        Item item = Item.createItem("나시",50000,30,"상세정보입니다아아아앙");

        Item savedItem = itemRepository.save(item);
        assertEquals("나시", savedItem.getName());
    }
    @Test
    @Rollback(false)
    void createItemList() throws Exception{
//        for(int i=1; i<=10; i++) {
//            Item item = new Item("나시"+i,50000+i,30+i,"상세정보입니다아아아앙"+i);
//            System.out.println(item);
//            Item savedItem = itemRepository.save(item);
//        }
    }

    @Test
    void findByItemNmTest() throws Exception{
//        this.createItemList();
//        List<Item> itemList = itemRepository.findByName("나시1");
//
//        if(itemList.size()>0){
//            assertEquals("나시", "나시");
//        }
//        else{
//            assertEquals("나시", "sktl");
//        }
    }
}