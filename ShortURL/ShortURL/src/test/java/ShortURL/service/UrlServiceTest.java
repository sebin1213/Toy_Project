package ShortURL.service;

import ShortURL.domain.Urls;
import ShortURL.module.Base62Encode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UrlServiceTest {
    @Autowired
    UrlService urlService;
    @Autowired
    Base62Encode base62Encode;

    @Test
    @Rollback(value = false)
    void create(){
        Urls url = new Urls();

        String urls = "https://velog.io/@hope1213";
        urls = urls.replace("https://","");
        Urls URL = urlService.createUrl("https://velog.io/@hope1213");
        assertEquals(urls, URL.getUrl());
    }
}