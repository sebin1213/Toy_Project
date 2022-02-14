package com.example.MiniShop.domain;

import com.example.MiniShop.domain.Enum.ItemStatus;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private long id;

    @NotNull
    @Column(length = 30)
    private String name;

    @NotNull
    private int price;

    private int stockQuantity = 0; // 재고수량

//    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
//    private List<ItemImg> itemImg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_img_id")
    private ItemImg itemImg;

    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus; //상품 판매 상태

    public static Item createItem(String name, int price, int stockQuantity, String itemDetail,ItemImg itemImg){
        Item item = new Item();
        item.name= name;
        item.price=price;
        item.stockQuantity=stockQuantity;
        item.itemDetail=itemDetail;
        item.itemImg = itemImg;
        return item;
    }
//    public static Item createItem(String name, int price, int stockQuantity, String itemDetail,List<ItemImg> itemImg){
//        Item item = new Item();
//        item.name= name;
//        item.price=price;
//        item.stockQuantity=stockQuantity;
//        item.itemDetail=itemDetail;
//        item.itemImg=itemImg;
//        return item;
//    }



    /***비즈니스 로직****/

    /**재고 추가**/

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**재고 감소**/
    public void removeStock(int quantity){
        if (this.stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
        }
        else{
            System.out.println("재고없음");
        }
    }

    /***현재 시간**/



}
