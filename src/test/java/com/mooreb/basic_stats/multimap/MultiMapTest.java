package com.mooreb.basic_stats.multimap;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiMapTest {

    @Test
    public void one() {
        MultiMap<String,String> mm = new MultiMap<>();
        mm.put("k", "v1");
        mm.put("k", "v2");
        List<String> vals = mm.get("k");
        Assert.assertNotNull(vals);
        Assert.assertEquals(vals.size(), 2, "expect two things in the list");
    }
}
