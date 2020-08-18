package com.mooreb.basic_stats.histogram;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BucketTest {

    @Test(groups="unit")
    public void bucketTest1() {
        final Bucket<Integer> bucket = Bucket.middle(0, 5, true, false);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertTrue(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertFalse(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest2() {
        final Bucket<Integer> bucket = Bucket.middle(0, 5, true, true);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertTrue(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertTrue(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest3() {
        final Bucket<Integer> bucket = Bucket.middle(0, 5, false, true);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertFalse(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertTrue(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest4() {
        final Bucket<Integer> bucket = Bucket.middle(0, 5, false, false);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertFalse(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertFalse(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest5() {
        final Bucket<Integer> bucket = Bucket.catchAllLeft(0, true);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertTrue(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertTrue(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertFalse(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertFalse(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertFalse(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertFalse(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest6() {
        final Bucket<Integer> bucket = Bucket.catchAllRight(0, true);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertTrue(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertTrue(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertTrue(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest7() {
        final Bucket<Integer> bucket = Bucket.catchAllLeft(0, false);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertTrue(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertFalse(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertFalse(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertFalse(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertFalse(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertFalse(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertFalse(sixBelongs);
    }

    @Test(groups="unit")
    public void bucketTest8() {
        final Bucket<Integer> bucket = Bucket.catchAllRight(0, false);
        final boolean negativeOneBelongs = bucket.belongsInThisBucket(-1);
        Assert.assertFalse(negativeOneBelongs);
        final boolean zeroBelongs = bucket.belongsInThisBucket(0);
        Assert.assertFalse(zeroBelongs);
        final boolean oneBelongs = bucket.belongsInThisBucket(1);
        Assert.assertTrue(oneBelongs);
        final boolean twoBelongs = bucket.belongsInThisBucket(2);
        Assert.assertTrue(twoBelongs);
        final boolean fourBelongs = bucket.belongsInThisBucket(4);
        Assert.assertTrue(fourBelongs);
        final boolean fiveBelongs = bucket.belongsInThisBucket(5);
        Assert.assertTrue(fiveBelongs);
        final boolean sixBelongs = bucket.belongsInThisBucket(6);
        Assert.assertTrue(sixBelongs);
    }
}
