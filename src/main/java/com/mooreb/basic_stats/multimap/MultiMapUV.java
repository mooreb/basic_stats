package com.mooreb.basic_stats.multimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MultiMapUV<K,V>  {
    private final Map<K, Set<V>> internalMap = new HashMap<>();

    public void put(K k, V v) {
        Set<V> set = internalMap.get(k);
        if(null == set) {
            set = new HashSet<V>();
            internalMap.put(k, set);
        }
        set.add(v);
    }

    public Set<V> get(K k) {
        return internalMap.get(k);
    }

    public Set<K> keySet() {
        return internalMap.keySet();
    }

    public Set<Map.Entry<K,Set<V>>> entries() {
        return internalMap.entrySet();
    }

    public Collection<Set<V>> values() {
        return internalMap.values();
    }

}
