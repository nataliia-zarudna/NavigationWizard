package com.zarudna.navigationwizard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsirobaba on 4/27/18.
 */

public class InMemoryCache {

    private Map<String, Object> cache;

    public InMemoryCache() {
        cache = new HashMap<>();
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

}
