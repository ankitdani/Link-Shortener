package com.example.urlshortener.Service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidatorService {
    public static final UrlValidatorService INSTANCE = new UrlValidatorService();
//    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private static final String URL_REGEX = "^(http|https):\\/\\/[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(\\/.*)?$|^([a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(\\/.*)?)$";


    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private UrlValidatorService() {
    }

    public boolean validateURL(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();

    }
}
