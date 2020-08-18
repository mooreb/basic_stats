package com.mooreb.basic_stats.histogram;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DoubleBucketListBuilderTest {
    @Test(groups="unit")
    public void builderTest1() {
        final DoubleBucketListBuilder doubleBucketListBuilder = new DoubleBucketListBuilder();
        final List<Bucket<Double>> buckets = doubleBucketListBuilder.buildFromNumBuckets(0, 10, 6);
        Assert.assertNotNull(buckets);
    }
}
