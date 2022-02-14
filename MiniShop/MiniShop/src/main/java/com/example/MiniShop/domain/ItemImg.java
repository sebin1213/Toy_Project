package com.example.MiniShop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ItemImg{

    @Id
    @GeneratedValue
    @Column(name="item_img_id")
    private Long id;

    private String name; //이미지 파일명

    private String oriImgName;

    private String imgUrl; //이미지 조회 경로

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;

    public ItemImg createItemImg(String imgName, String oriImgName, String imgUrl) {
        this.name = imgName;
        this.imgUrl = imgUrl;
        this.oriImgName=oriImgName;
//        this.item = item;
        return this;
    }

    public void updateItemImg(String imgName, String imgUrl) {
        this.name = imgName;
        this.imgUrl = imgUrl;
    }
}