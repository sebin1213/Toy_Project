package ShortURL.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Urls {
    @Id @GeneratedValue
    private int id;

    private String url;

    private String shortUrl;

    private long reqCount;

}
