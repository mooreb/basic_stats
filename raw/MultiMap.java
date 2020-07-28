package com.mooreb.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap<K,V> {
    private final Map<K,List<V>> internalMap = new HashMap<>();

    public void put(K k, V v) {
        List<V> list = internalMap.get(k);
        if(null == list) {
            list = new ArrayList<V>();
            internalMap.put(k, list);
        }
        list.add(v);
    }

    public void place(K k, List<V> vs) {
        internalMap.put(k, vs);
    }

    public List<V> get(K k) {
        return internalMap.get(k);
    }

    public Set<K> keySet() {
        return internalMap.keySet();
    }

    public Set<Map.Entry<K,List<V>>> entries() {
        return internalMap.entrySet();
    }

    public Collection<List<V>> values() {
        return internalMap.values();
    }

    /** Count the total number of entries in this multimap;
     *
     * @return the sum of the length of all the lists in the map
     */
    public long count() {
        long retval = 0;
        for(List<V> v : internalMap.values()) {
            retval += v.size();
        }
        return retval;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Set<K> keySet = internalMap.keySet();

        for(final K k : keySet()) {
            final List<V> vs = internalMap.get(k);
            sb.append(k);
            sb.append("[");
            sb.append(vs.size());
            sb.append("]");
            sb.append(": ");
            final Iterator<V> iterator = vs.iterator();
            while(iterator.hasNext()) {
                V v = iterator.next();
                sb.append(v);
                if(iterator.hasNext()) {
                    sb.append(", ");
                }
                else {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
