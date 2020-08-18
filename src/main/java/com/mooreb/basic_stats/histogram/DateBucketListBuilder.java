package com.mooreb.basic_stats.histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DateBucketListBuilder {
    public List<Bucket<Date>> buildFromTimeSpan(final Date minDate, final Date maxDate, long span, TimeUnit timeUnit) {
        check(minDate, maxDate);
        final List<Bucket<Date>> retval = new ArrayList<>();
        final boolean inclusiveLower = true;
        final boolean inclusiveUpper = false;
        final Bucket<Date> left = Bucket.catchAllLeft(minDate, inclusiveUpper);
        retval.add(left);
        Date low = minDate;
        Date candidate = new Date(low.getTime() + timeUnit.toMillis(span));
        while (candidate.before(maxDate)) {
            final Bucket<Date> bucket = Bucket.middle(low, candidate, inclusiveLower, inclusiveUpper);
            retval.add(bucket);
            low = candidate;
            candidate = new Date(low.getTime() + timeUnit.toMillis(span));
        }
        final Bucket<Date> lastMiddle = Bucket.middle(low, maxDate, inclusiveLower, true);
        retval.add(lastMiddle);
        final Bucket<Date> right = Bucket.catchAllRight(maxDate, false);
        retval.add(right);
        return Collections.unmodifiableList(retval);
    }
    public List<Bucket<Date>> buildFromNumBuckets(final Date minDate, final Date maxDate, int numBuckets) {
        check(minDate, maxDate);
        if(numBuckets <= 0) throw new IllegalArgumentException("numBuckets must be positive");
        final long minDateMillis = minDate.getTime();
        final long maxDateMillis = maxDate.getTime();
        final long spanMillis = maxDateMillis - minDateMillis;
        final long perBucketMillis = spanMillis/numBuckets;
        return buildFromTimeSpan(minDate, maxDate, perBucketMillis, TimeUnit.MILLISECONDS);
    }

    private void check(final Date minDate, final Date maxDate) {
        if(null == minDate) throw new IllegalArgumentException("min date cannot be null");
        if(null == maxDate) throw new IllegalArgumentException("max date cannot be null");
        if(!minDate.before(maxDate)) throw new IllegalArgumentException("min date has to be before max date");
    }
}
