package com.example.MiniShop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ItemImg{

    @Id
    @GeneratedValue
    @Column(name="item_img_id")
    private Long id;

    private String Name; //이미지 파일명


    private String imgUrl; //이미지 조회 경로

    private String repimgUrl; //대표 이미지 여부

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.Name = imgName;
        this.imgUrl = imgUrl;
    }
}