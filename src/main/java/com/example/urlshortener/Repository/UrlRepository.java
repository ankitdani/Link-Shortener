package com.example.urlshortener.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class UrlRepository {
    private final Jedis jedis;
    private final String idKey;
    private final String urlKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlRepository.class);

    public UrlRepository() {
        this.jedis = new Jedis();
        this.idKey = "id";
        this.urlKey = "url:";
    }

    public UrlRepository(Jedis jedis, String idKey, String urlKey) {
        this.jedis = jedis;
        this.idKey = idKey;
        this.urlKey = urlKey;
    }

    public Long incrementID() {
        Long id = jedis.incr(idKey);
        LOGGER.info("Incrementing ID: {}", id - 1);
        return id - 1;
    }

    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
        jedis.hset(urlKey, key, longUrl);
    }

    public String getUrl(Long id) throws UrlNotFoundException {
        LOGGER.info("Retrieving at {}", id);
        String url = jedis.hget(urlKey, "url:" + id);
        LOGGER.info("Retrieved {} at {}", url, id);
        if (url == null) {
            throw new UrlNotFoundException("URL at key" + id + " does not exist");
        }
        return url;
    }
}

class UrlNotFoundException extends Exception {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
