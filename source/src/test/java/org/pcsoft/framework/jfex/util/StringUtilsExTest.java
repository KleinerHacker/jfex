package org.pcsoft.framework.jfex.util;

import org.junit.Assert;
import org.junit.Test;


public class StringUtilsExTest {
    @Test
    public void insert() throws Exception {
        Assert.assertEquals("123abc45", StringUtilsEx.insert("12345", "abc", 3));
        Assert.assertEquals("abc12345", StringUtilsEx.insert("12345", "abc", 0));
        Assert.assertEquals("12345abc", StringUtilsEx.insert("12345", "abc", 5));

        try {
            StringUtilsEx.insert("12345", "abc", 6);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        try {
            StringUtilsEx.insert("12345", "abc", -1);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}