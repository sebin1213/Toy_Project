package ShortURL.module;

import org.springframework.stereotype.Component;

@Component
public class Base62Encode {
    private final String URL = "http://";
    private final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String Encoding(int num) {

        StringBuffer encd = new StringBuffer();
        if (num==0){
            encd.append(0);
        }
        while (num > 0){
            encd.append(chars.charAt((int)(num % 62)));
            num /= 62;
        }
        return(URL + encd.toString());
    }

    private long Decoding(String url){
        long sum = 0;
        long squar = 1;
        for (int i = 0; i < url.length(); i++) {
            sum += chars.indexOf(url.charAt(i)) * squar;
            squar *= 62;
        }
        return sum;
    }
}