package com.zarudna.navigationwizard.model.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory cache
 */

public class InMemoryCache {

    private Map<String, Object> cache;

    public InMemoryCache() {
        cache = new HashMap<>();
    }

    /**
     * Put data to cache
     * @param key key of data
     * @param value value
     */
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * Get data from cache
     * @param key key of data
     * @return value
     */
    public Object get(String key) {
        return cache.get(key);
    }

}
