package com.mooreb.util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DateBucketListBuilderTest {

    @Test(groups="unit")
    public void testBuildFromTimeSpan1() {
        // $ date -r 1596565870
        // Tue Aug  4 11:31:10 PDT 2020
        // $ date -r 1596567557
        // Tue Aug  4 11:59:17 PDT 2020
        final Date from = new Date(1596565870000L);
        final Date to = new Date(1596567557000L);
        final DateBucketListBuilder dateBucketListBuilder = new DateBucketListBuilder();
        final List<Bucket<Date>> buckets = dateBucketListBuilder.buildFromTimeSpan(from, to, 5, TimeUnit.MINUTES);
        Assert.assertNotNull(buckets);
    }

    @Test(groups="unit")
    public void testBuildFromNumBuckets1() {
        // $ date -r 1596565870
        // Tue Aug  4 11:31:10 PDT 2020
        // $ date -r 1596567557
        // Tue Aug  4 11:59:17 PDT 2020
        final Date from = new Date(1596565870000L);
        final Date to = new Date(1596567557000L);
        final DateBucketListBuilder dateBucketListBuilder = new DateBucketListBuilder();
        final List<Bucket<Date>> buckets = dateBucketListBuilder.buildFromNumBuckets(from, to, 5);
        Assert.assertNotNull(buckets);
    }

}
