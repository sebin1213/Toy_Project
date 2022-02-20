package ShortURL.service;

import ShortURL.domain.Urls;
import ShortURL.module.Base62Encode;
import ShortURL.module.UrlValidation;
import ShortURL.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final Base62Encode base62Encode;
    private final UrlValidation urlValidation;

//    @Transactional
    public Urls createUrl(String urls){
        if(!urlValidation.Validation(urls)){
            throw new IllegalArgumentException("잘못된 URL 타입입니다.");
        }
        Urls url = new Urls();
        url.setUrl(urls.replace("https://",""));
        url.setShortUrl(base62Encode.Encoding(url.getId()));
        urlRepository.save(url);
        return url;
    }
}
