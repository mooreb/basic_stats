package com.mooreb.util;

import java.util.ArrayList;
import java.util.List;

public class Histogram<T extends Comparable<T>> {
    private final List<Bucket<T>> buckets;
    final Counter<Bucket<T>> bucketCounter;

    public Histogram(final List<Bucket<T>> buckets) {
        this.buckets = new ArrayList<>(buckets);
        this.bucketCounter = new Counter<>();
    }

    public void bin(final List<T> observations) {
        for(final T observation : observations) {
            boolean incremented = false;
            for(final Bucket<T> bucket : buckets) {
                if(bucket.belongsInThisBucket(observation)) {
                    bucketCounter.count(bucket);
                    incremented = true;
                    break;
                }
            }
            if(!incremented) {
                throw new IllegalStateException("could not place observation " + observation + " in a bucket: " + buckets);
            }
        }
    }

    public List<Counted<Bucket<T>>> getBuckets() {
        final List<Counted<Bucket<T>>> frequencyTable = bucketCounter.getFrequencyTable();
        return frequencyTable;
    }

    public Counted<Bucket<T>> getBiggestBucket() {
        final List<Counted<Bucket<T>>> frequencyTable = getBuckets();
        return frequencyTable.get(0);
    }

}
