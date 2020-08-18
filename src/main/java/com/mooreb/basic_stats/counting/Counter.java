package com.mooreb.basic_stats.counting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Counter<T> {
    private final Map<T, Long> internalMap = new ConcurrentHashMap<>();
    long numEntries=0;
    long numNullEntries = 0;

    public synchronized void count(final T t) {
        numEntries++;
        if(null == t) {
            numNullEntries++;
            return;
        }
        if(internalMap.containsKey(t)) {
            internalMap.put(t, 1+internalMap.get(t));
        }
        else {
            internalMap.put(t, 1L);
        }
    }

    public synchronized List<Counted<T>> getFrequencyTable() {
        final List<Counted<T>> retval = new ArrayList<>();
        for(final Map.Entry<T, Long> entry : internalMap.entrySet()) {
            final T t = entry.getKey();
            final long count = entry.getValue();
            final Counted<T> counted = new Counted<>(t, count, numEntries, numNullEntries);
            retval.add(counted);
        }
        if(numNullEntries > 0) {
            retval.add(new Counted(null, numNullEntries, numEntries, numNullEntries));
        }
        Collections.sort(retval);
        return Collections.unmodifiableList(retval);
    }

    public long getCount(final T t) {
        if(null == t) return numNullEntries;
        final Long retval = internalMap.get(t);
        if(null == retval) return 0L;
        return retval;
    }

    public long getNumEntries() {
        return numEntries;
    }

    public long getNumNullEntries() {
        return numNullEntries;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Counter{");
        sb.append("internalMap=").append(internalMap);
        sb.append(", numEntries=").append(numEntries);
        sb.append(", numNullEntries=").append(numNullEntries);
        sb.append('}');
        return sb.toString();
    }
}
