package ShortURL.repository;

import ShortURL.domain.Urls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Urls,Long> {
    Urls findByUrl(String url);
    Urls findByShortUrl(String ShortUrl);
}
