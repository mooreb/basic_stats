package com.mooreb.basic_stats.histogram;

import com.mooreb.basic_stats.counting.Counted;
import com.mooreb.basic_stats.counting.Counter;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Counted<Bucket<T>>> getBucketsByFrequency() {
        final List<Counted<Bucket<T>>> frequencyTable = bucketCounter.getFrequencyTable();
        return frequencyTable;
    }

    public Counted<Bucket<T>> getBiggestBucket() {
        final List<Counted<Bucket<T>>> frequencyTable = getBucketsByFrequency();
        return frequencyTable.get(0);
    }

    public List<Counted<Bucket<T>>> getBucketsByNaturalOrdering() {
        final List<Counted<Bucket<T>>> retval = new ArrayList<>();
        final long numEntries = bucketCounter.getNumEntries();
        final long numNullEntries = bucketCounter.getNumNullEntries();
        for(final Bucket<T> bucket : buckets) {
            final long count = bucketCounter.getCount(bucket);
            final Counted<Bucket<T>> counted = new Counted<>(bucket, count, numEntries, numNullEntries);
            retval.add(counted);
        }
        return Collections.unmodifiableList(retval);
    }

}
