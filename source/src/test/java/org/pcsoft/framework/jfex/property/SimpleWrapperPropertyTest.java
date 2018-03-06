package org.pcsoft.framework.jfex.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SimpleWrapperPropertyTest {
    @Test
    public void test() {
        final StringProperty str = new SimpleStringProperty();
        final ObjectProperty<Integer> value = new SimpleWrapperProperty<Integer, String>(str) {
            @Override
            protected Integer convertTo(String value) {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            @Override
            protected String convertFrom(Integer value) {
                return value == null ? StringUtils.EMPTY : String.valueOf(value);
            }
        };

        Assert.assertNull(str.get());
        Assert.assertNull(value.get());

        str.set("123");
        Assert.assertEquals("123", str.get());
        Assert.assertEquals(123, value.get().intValue());

        value.set(321);
        Assert.assertEquals("321", str.get());
        Assert.assertEquals(321, value.get().intValue());

        str.set("abc");
        Assert.assertEquals("abc", str.get());
        Assert.assertNull(value.get());

        value.set(null);
        Assert.assertNull(str.get());
        Assert.assertNull(value.get());
    }
}
