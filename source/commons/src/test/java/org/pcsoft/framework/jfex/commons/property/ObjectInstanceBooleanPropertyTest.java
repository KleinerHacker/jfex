package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jfex.commons.property.ObjectInstanceBooleanProperty;

import java.util.concurrent.atomic.AtomicInteger;


public class ObjectInstanceBooleanPropertyTest {

    private static final String CACHE = "Cache String";

    private static final String CACHE1 = "Cache String 1";
    private static final String CACHE2 = "Cache String 2";

    @Test
    public void testSimple() {
        final AtomicInteger strCounter = new AtomicInteger(0), instCounter = new AtomicInteger(0);

        final ObjectProperty<String> stringProperty = new SimpleObjectProperty<>("Hello World");
        final ObjectInstanceBooleanProperty<String> property = new ObjectInstanceBooleanProperty<>(stringProperty, CACHE);

        stringProperty.addListener((v, o, n) -> strCounter.incrementAndGet());
        property.addListener((v, o, n) -> instCounter.incrementAndGet());

        Assert.assertTrue(property.get());
        Assert.assertEquals(0, strCounter.get());
        Assert.assertEquals(0, instCounter.get());
        Assert.assertEquals("Hello World", stringProperty.get());

        stringProperty.set("What you are?");

        Assert.assertTrue(property.get());
        Assert.assertEquals(1, strCounter.get());
        Assert.assertEquals(0, instCounter.get());
        Assert.assertEquals("What you are?", stringProperty.get());

        property.set(false);

        Assert.assertFalse(property.get());
        Assert.assertEquals(2, strCounter.get());
        Assert.assertEquals(1, instCounter.get());
        Assert.assertNull(stringProperty.get());

        property.set(true);

        Assert.assertTrue(property.get());
        Assert.assertEquals(3, strCounter.get());
        Assert.assertEquals(2, instCounter.get());
        Assert.assertEquals(CACHE, stringProperty.get());

        stringProperty.set(null);

        Assert.assertFalse(property.get());
        Assert.assertEquals(4, strCounter.get());
        Assert.assertEquals(3, instCounter.get());
        Assert.assertNull(stringProperty.get());
    }

    @Test
    public void testMulti() {
        final AtomicInteger instCounter = new AtomicInteger(0);

        final ObjectProperty<String> stringProperty1 = new SimpleObjectProperty<>("Hello World");
        final ObjectProperty<String> stringProperty2 = new SimpleObjectProperty<>("Yoda");
        final ObjectInstanceBooleanProperty<String> property = new ObjectInstanceBooleanProperty<>(
                new ObjectInstanceBooleanProperty.ValuePair(stringProperty1, CACHE1),
                new ObjectInstanceBooleanProperty.ValuePair(stringProperty2, CACHE2)
        );

        property.addListener((v, o, n) -> instCounter.incrementAndGet());

        Assert.assertTrue(property.get());
        Assert.assertEquals(0, instCounter.get());
        Assert.assertEquals("Hello World", stringProperty1.get());
        Assert.assertEquals("Yoda", stringProperty2.get());

        stringProperty2.set("Boom");

        Assert.assertTrue(property.get());
        Assert.assertEquals(0, instCounter.get());
        Assert.assertEquals("Hello World", stringProperty1.get());
        Assert.assertEquals("Boom", stringProperty2.get());

        property.set(false);

        Assert.assertFalse(property.get());
        Assert.assertEquals(1, instCounter.get());
        Assert.assertNull(stringProperty1.get());
        Assert.assertNull(stringProperty2.get());

        property.set(true);

        Assert.assertTrue(property.get());
        Assert.assertEquals(2, instCounter.get());
        Assert.assertEquals(CACHE1, stringProperty1.get());
        Assert.assertEquals(CACHE2, stringProperty2.get());

        property.set(false);

        Assert.assertFalse(property.get());
        Assert.assertEquals(3, instCounter.get());
        Assert.assertNull(stringProperty1.get());
        Assert.assertNull(stringProperty2.get());

        stringProperty1.set("Hello");

        Assert.assertFalse(property.get());
        Assert.assertEquals(3, instCounter.get());
        Assert.assertEquals("Hello", stringProperty1.get());
        Assert.assertNull(stringProperty2.get());

        stringProperty2.set("World");

        Assert.assertTrue(property.get());
        Assert.assertEquals(4, instCounter.get());
        Assert.assertEquals("Hello", stringProperty1.get());
        Assert.assertEquals("World", stringProperty2.get());
    }

}
