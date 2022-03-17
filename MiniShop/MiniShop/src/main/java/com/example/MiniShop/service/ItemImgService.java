package com.example.MiniShop.service;

import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import com.example.MiniShop.repository.ItemImgRepository;
import com.example.MiniShop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImgService {
    private final ItemImgRepository itemImgRepository;
    private final ItemRepository itemRepository;

    public ItemImg createImg(MultipartFile itemImgFileList){

        // 파일 이름을 업로드 한 일자로 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String createDate = simpleDateFormat.format(new Date());

        // 경로를 지정
        String path = "src/main/resources/static/images/" + createDate;

        File file = new File(path);
        // 저장할 위치의 디렉토리가 존재하지 않는다면
        if (!file.exists()) {
            //디렉토리가 존재하지 않을 때 생성
            file.mkdirs();
        }
        String contentType = itemImgFileList.getContentType();
        String c_Type = ".jpg";
        if(contentType.equals("image/jpeg")){
            c_Type = ".jpg";
        }
        else if(contentType.equals("image/png")){
            c_Type = ".png";
        }
        else if(contentType.equals("image/gif")){
            c_Type = ".gif";
        }

        String fileName = LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss.SSS")).toString();
        Path savePath = Paths.get(path +"/"+ fileName + c_Type);
        try {
            itemImgFileList.transferTo(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ItemImg itemImg = new ItemImg();
        ItemImg Img = itemImg.createItemImg(fileName,itemImgFileList.getOriginalFilename(),"/images/"+createDate+"/"+fileName+ c_Type);
        itemImgRepository.save(Img);
        return Img;

//        ItemImg itemImg = new ItemImg();
//        ItemImg Img = itemImg.createItemImg(itemImgFileList.getName(),itemImgFileList.getOriginalFilename(),"/itemImg/"+itemImgFileList.getOriginalFilename());
//        itemImgRepository.save(Img);
//
////        for(MultipartFile itemImgFile : itemImgFileList){
////            ItemImg itemImg = new ItemImg();
////            ItemImg Img = itemImg.createItemImg(itemImgFile.getName(),itemImgFile.getOriginalFilename(),"/src/img/"+itemImgFile.getName(),itemRepository.getById(1l));
////            itemImgRepository.save(Img);
////        }
//        return Img;
    }
}
