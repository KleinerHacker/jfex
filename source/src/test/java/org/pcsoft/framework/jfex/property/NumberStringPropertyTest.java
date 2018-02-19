package org.pcsoft.framework.jfex.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Test;


public class NumberStringPropertyTest {

    @Test
    public void testIntegerAsNumber() {
        final IntegerProperty integerProperty = new SimpleIntegerProperty(0);
        final Property<String> stringProperty = new NumberStringProperty<>(integerProperty, Number.class);

        Assert.assertEquals(0, integerProperty.get());
        Assert.assertEquals("0", stringProperty.getValue());

        integerProperty.set(99);

        Assert.assertEquals(99, integerProperty.get());
        Assert.assertEquals("99", stringProperty.getValue());

        stringProperty.setValue("103");

        Assert.assertEquals(103, integerProperty.get());
        Assert.assertEquals("103", stringProperty.getValue());

        stringProperty.setValue(null);

        Assert.assertEquals(0, integerProperty.get());
        Assert.assertEquals("0", stringProperty.getValue());
    }

    @Test
    public void testDoubleAsNumber() {
        final DoubleProperty doubleProperty = new SimpleDoubleProperty(0d);
        final Property<String> stringProperty = new NumberStringProperty<>(doubleProperty, Number.class);

        Assert.assertEquals(0d, doubleProperty.get(), 0d);
        Assert.assertEquals("0.0", stringProperty.getValue());

        doubleProperty.set(9.9d);

        Assert.assertEquals(9.9d, doubleProperty.get(), 0d);
        Assert.assertEquals("9.9", stringProperty.getValue());

        stringProperty.setValue("10.3");

        Assert.assertEquals(10.3d, doubleProperty.get(), 0d);
        Assert.assertEquals("10.3", stringProperty.getValue());

        stringProperty.setValue(null);

        Assert.assertEquals(0d, doubleProperty.get(), 0d);
        Assert.assertEquals("0.0", stringProperty.getValue());
    }

    @Test
    public void testInteger() {
        final ObjectProperty<Integer> integerProperty = new SimpleObjectProperty<>(0);
        final Property<String> stringProperty = new NumberStringProperty<>(integerProperty, Integer.class);

        Assert.assertEquals(Integer.valueOf(0), integerProperty.get());
        Assert.assertEquals("0", stringProperty.getValue());

        integerProperty.set(99);

        Assert.assertEquals(Integer.valueOf(99), integerProperty.get());
        Assert.assertEquals("99", stringProperty.getValue());

        stringProperty.setValue("103");

        Assert.assertEquals(Integer.valueOf(103), integerProperty.get());
        Assert.assertEquals("103", stringProperty.getValue());

        stringProperty.setValue(null);

        Assert.assertNull(integerProperty.get());
        Assert.assertNull(stringProperty.getValue());
    }
}
