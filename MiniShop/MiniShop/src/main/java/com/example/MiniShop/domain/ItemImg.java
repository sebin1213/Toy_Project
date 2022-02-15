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

    public static ItemImg createItemImg(String imgName, String oriImgName, String imgUrl) {
        ItemImg itemImg = new ItemImg();
        itemImg.name = imgName;
        itemImg.imgUrl = imgUrl;
        itemImg.oriImgName=oriImgName;
        return itemImg;
    }

    public void updateItemImg(String imgName, String imgUrl) {
        this.name = imgName;
        this.imgUrl = imgUrl;
    }
}