package ShortURL.module;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;

@Component
public class UrlValidation {
    public static Boolean Validation(String url){
        try {
            URL connectionUrl = new URL(url); //url형식이 잘못된경우 exception 발생
            URLConnection conn = connectionUrl.openConnection();
            conn.connect();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
