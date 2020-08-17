package com.mooreb.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleBucketListBuilder {
    public List<Bucket<Double>> buildFromNumBuckets(final double min, final double max, int numBuckets) {
        if(numBuckets <= 0) throw new IllegalArgumentException("numBuckets must be positive");
        if(min >= max) throw new IllegalArgumentException("min must be less than max");
        final double width = ((max-min)/numBuckets);
        final List<Bucket<Double>> retval = new ArrayList<>();
        retval.add(Bucket.catchAllLeft(min, false));

        final double finalCandidateHigh = max-width;
        double candidateLow = min;
        double candidateHigh = candidateLow + width;
        while(candidateHigh <= finalCandidateHigh) {
            final Bucket<Double> bucket = Bucket.middle(candidateLow, candidateHigh, true, false);
            retval.add(bucket);
            candidateLow = candidateHigh;
            candidateHigh += width;
        }
        retval.add(Bucket.middle(finalCandidateHigh, max, true, true));
        retval.add(Bucket.catchAllRight(max, false));
        return Collections.unmodifiableList(retval);
    }
}
