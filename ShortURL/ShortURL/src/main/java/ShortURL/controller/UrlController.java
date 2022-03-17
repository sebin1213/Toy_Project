package ShortURL.controller;

import ShortURL.domain.Urls;
import ShortURL.module.Base62Encode;
import ShortURL.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/")
    public String ShortEncoding(@Validated UrlForm urlForm, BindingResult result){
        if (result.hasErrors()){
            return "/";
        }
        urlService.createUrl(urlForm.getUrl());

        return "/";
    }
}
