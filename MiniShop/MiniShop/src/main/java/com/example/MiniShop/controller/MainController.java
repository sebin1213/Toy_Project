package com.example.MiniShop.controller;

import com.example.MiniShop.controller.form.ItemForm;
import com.example.MiniShop.controller.form.ItemImgForm;
import com.example.MiniShop.domain.Item;
import com.example.MiniShop.domain.ItemImg;
import com.example.MiniShop.service.ItemImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemImgService itemImgService;

    @GetMapping(value = "/shop")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping(value = "/test")
    public String test(Model model){
        return "thymeleaf_Test";
    }

    @PostMapping(value = "/test")
    public String createBoard(@RequestParam("itemImgFileList") MultipartFile itemImgFileList
    ) throws Exception {
        itemImgService.createImg(itemImgFileList);
        return "thymeleaf_Test";
    }
}
