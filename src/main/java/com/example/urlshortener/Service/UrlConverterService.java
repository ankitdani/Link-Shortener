package com.example.urlshortener.Service;


import com.example.urlshortener.Repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlConverterService.class);
    private final UrlRepository urlRepository;

    public UrlConverterService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String localURL, String longUrl) {
        LOGGER.info("Shortening {}", longUrl);
        Long id = urlRepository.incrementID();
        String uniqueID = IdConverterService.INSTANCE.createUniqueID(id);
        urlRepository.saveUrl("url:"+id, longUrl);
        String baseString = formatLocalURLFromShortener(localURL);
//        String shortenedURL = baseString + uniqueID;
        return (baseString + uniqueID);
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
        Long dictionaryKey = IdConverterService.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        String longUrl = urlRepository.getUrl(dictionaryKey);
        LOGGER.info("Converting shortened URL back to {}", longUrl);
        return longUrl;
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}
