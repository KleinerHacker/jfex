package org.pcsoft.framework.jfex.util;

import org.junit.Assert;
import org.junit.Test;


public class HexUtilsTest {

    @Test
    public void testToHexString1() {
        final String string = HexUtils.toHexString(new byte[]{0x00, (byte) 0xFF});
        Assert.assertEquals("00FF", string);
    }

    @Test
    public void testToHexString2() {
        final String string = HexUtils.toHexString(new byte[0]);
        Assert.assertEquals("", string);
    }

    @Test
    public void testToHexString3() {
        final String string = HexUtils.toHexString(null);
        Assert.assertNull(string);
    }

    @Test
    public void testToByteArray1() {
        final byte[] bytes = HexUtils.toByteArray("00FF");
        Assert.assertArrayEquals(new byte[]{0x00, (byte) 0xFF}, bytes);
    }

    @Test
    public void testToByteArray2() {
        final byte[] bytes = HexUtils.toByteArray("F00");
        Assert.assertArrayEquals(new byte[] {0x0F, 0x00}, bytes);
    }

    @Test
    public void testToByteArray3() {
        final byte[] bytes = HexUtils.toByteArray("");
        Assert.assertArrayEquals(new byte[0], bytes);
    }

    @Test
    public void testToByteArray4() {
        final byte[] bytes = HexUtils.toByteArray(null);
        Assert.assertNull(bytes);
    }

}
