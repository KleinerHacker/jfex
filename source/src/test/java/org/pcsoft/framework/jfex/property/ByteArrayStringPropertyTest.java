package org.pcsoft.framework.jfex.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;


public class ByteArrayStringPropertyTest {

    @Test
    public void testHex() {
        final ObjectProperty<byte[]> byteArray = new SimpleObjectProperty<>(new byte[] {0x00, (byte)0xFF});
        final Property<String> string = new ByteArrayStringProperty(byteArray);

        Assert.assertArrayEquals(new byte[] {0x00, (byte)0xFF}, byteArray.get());
        Assert.assertEquals("00FF", string.getValue());

        byteArray.set(new byte[]{0x0F});

        Assert.assertArrayEquals(new byte[]{0x0F}, byteArray.get());
        Assert.assertEquals("0F", string.getValue());

        string.setValue("0080FF");

        Assert.assertArrayEquals(new byte[]{0x00, (byte) 0x80, (byte) 0xFF}, byteArray.get());
        Assert.assertEquals("0080FF", string.getValue());

        string.setValue(null);

        Assert.assertNull(byteArray.get());
        Assert.assertNull(string.getValue());
    }

    @Test
    public void testBase64() throws Exception {
        final byte[] bytes_01 = "Hello World".getBytes("UTF-8");
        final String base64_01 = Base64.encodeBase64String(bytes_01);
        final byte[] bytes_02 = "What are you doing".getBytes("UTF-8");
        final String base64_02 = Base64.encodeBase64String(bytes_02);
        final byte[] bytes_03 = "Do foo".getBytes("UTF-8");
        final String base64_03 = Base64.encodeBase64String(bytes_03);

        final ObjectProperty<byte[]> byteArray = new SimpleObjectProperty<>(bytes_01);
        final Property<String> string = new ByteArrayStringProperty(byteArray, ByteArrayStringProperty.StringRepresentation.Base64);

        Assert.assertArrayEquals(bytes_01, byteArray.get());
        Assert.assertEquals(base64_01, string.getValue());

        byteArray.set(bytes_02);

        Assert.assertArrayEquals(bytes_02, byteArray.get());
        Assert.assertEquals(base64_02, string.getValue());

        string.setValue(base64_03);

        Assert.assertArrayEquals(bytes_03, byteArray.get());
        Assert.assertEquals(base64_03, string.getValue());

        string.setValue(null);

        Assert.assertNull(byteArray.get());
        Assert.assertNull(string.getValue());
    }

}
