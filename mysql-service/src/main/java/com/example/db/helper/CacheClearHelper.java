package com.example.db.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheClearHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheClearHelper.class);
    private final CacheManager cacheManager;

    public CacheClearHelper(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void clearCaches(String... caches){
        for(String cache:caches){
            cacheManager.getCache(cache).invalidate();
            LOGGER.info(String.format("Cleared cache '%s'", cache));
        }
    }
}
