package org.pcsoft.framework.jfex.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ObjectInstanceEnumerationPropertyTest {

    private static final String CACHE_STRING = "CACHE";
    private static final int CACHE_INTEGER = Integer.MAX_VALUE;
    private static final double CACHE_DOUBLE = Double.MAX_VALUE;

    private enum TestEnumeration {
        ValueString,
        ValueInteger,
        ValueDouble
    }

    @Test
    public void test() {
        final ObjectProperty<String> stringProperty = new SimpleObjectProperty<>("Hello World");
        final ObjectProperty<Integer> integerProperty = new SimpleObjectProperty<>(null);
        final ObjectProperty<Double> doubleProperty = new SimpleObjectProperty<>(null);

        final Map<TestEnumeration, ObjectInstanceEnumerationProperty.Value> map = new HashMap<>();
        map.put(TestEnumeration.ValueString, new ObjectInstanceEnumerationProperty.Value(stringProperty, CACHE_STRING));
        map.put(TestEnumeration.ValueInteger, new ObjectInstanceEnumerationProperty.Value(integerProperty, CACHE_INTEGER));
        map.put(TestEnumeration.ValueDouble, new ObjectInstanceEnumerationProperty.Value(doubleProperty, CACHE_DOUBLE));
        final ObjectInstanceEnumerationProperty<TestEnumeration> enumerationProperty = new ObjectInstanceEnumerationProperty<>(map);

        Assert.assertEquals("Hello World", stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertEquals(TestEnumeration.ValueString, enumerationProperty.get());

        enumerationProperty.set(TestEnumeration.ValueDouble);

        Assert.assertNull(stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertEquals(CACHE_DOUBLE, doubleProperty.get().doubleValue(), 0d);
        Assert.assertEquals(TestEnumeration.ValueDouble, enumerationProperty.get());

        integerProperty.set(100);

        Assert.assertNull(stringProperty.get());
        Assert.assertEquals(Integer.valueOf(100), integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertEquals(TestEnumeration.ValueInteger, enumerationProperty.get());

        integerProperty.set(null);

        Assert.assertNull(stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertNull(enumerationProperty.get());

        doubleProperty.set(0.9d);

        Assert.assertNull(stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertEquals(0.9d, doubleProperty.get(), 0d);
        Assert.assertEquals(TestEnumeration.ValueDouble, enumerationProperty.get());

        enumerationProperty.set(null);

        Assert.assertNull(stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertNull(enumerationProperty.get());

        integerProperty.set(100);

        Assert.assertNull(stringProperty.get());
        Assert.assertEquals(Integer.valueOf(100), integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertEquals(TestEnumeration.ValueInteger, enumerationProperty.get());

        integerProperty.set(null);

        Assert.assertNull(stringProperty.get());
        Assert.assertNull(integerProperty.get());
        Assert.assertNull(doubleProperty.get());
        Assert.assertNull(enumerationProperty.get());
    }

}
