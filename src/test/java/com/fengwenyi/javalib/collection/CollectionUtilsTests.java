package com.fengwenyi.javalib.collection;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author Erwin Feng
 * @since 2020/8/26
 */
public class CollectionUtilsTests {

    @Test
    public void testArrayEmpty() {
        String [] array = new String[0];
        boolean result = CollectionUtils.isEmpty(array);
        Assert.assertTrue(result);

        array = new String[1];
        result = CollectionUtils.isEmpty(array);
        Assert.assertFalse(result);

        result = CollectionUtils.isNotEmpty(array);
        Assert.assertTrue(result);

    }

}
