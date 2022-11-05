package com.ultimismc.skywars.core.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class UserCacheHandler<K, V> {

    protected final Map<K, V> userCache = new HashMap<>();

    public V getCache(K key) {
        return userCache.get(key);
    }

    public void addCache(K key, V value) {
        userCache.put(key, value);
    }

    public Collection<V> getCacheCollection() {
        return userCache.values();
    }
}
