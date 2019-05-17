package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jfex.commons.property.BooleanEnumerationProperty;

import java.util.concurrent.atomic.AtomicInteger;


public class BooleanEnumerationPropertyTest {
    private enum TestEnum {
        One,
        Two,
        Three
    }

    @Test
    public void test() {
        final AtomicInteger counter = new AtomicInteger(0);

        final SimpleBooleanProperty oneProp = new SimpleBooleanProperty();
        final SimpleBooleanProperty twoProp = new SimpleBooleanProperty();
        final SimpleBooleanProperty threeProp = new SimpleBooleanProperty();

        final ObjectProperty<TestEnum> prop = new BooleanEnumerationProperty<>(
                TestEnum.Three,
                new BooleanEnumerationProperty.Value<>(TestEnum.One, oneProp),
                new BooleanEnumerationProperty.Value<>(TestEnum.Two, twoProp),
                new BooleanEnumerationProperty.Value<>(TestEnum.Three, threeProp)
        );
        prop.addListener(o -> counter.incrementAndGet());

        Assert.assertEquals(TestEnum.Three, prop.get());
        Assert.assertFalse(oneProp.get());
        Assert.assertFalse(twoProp.get());
        Assert.assertTrue(threeProp.get());
        Assert.assertEquals(0, counter.get());

        twoProp.set(true);

        Assert.assertEquals(TestEnum.Two, prop.get());
        Assert.assertFalse(oneProp.get());
        Assert.assertTrue(twoProp.get());
        Assert.assertFalse(threeProp.get());
        Assert.assertEquals(2, counter.get());

        twoProp.set(false);

        Assert.assertNull(prop.get());
        Assert.assertFalse(oneProp.get());
        Assert.assertFalse(twoProp.get());
        Assert.assertFalse(threeProp.get());
        Assert.assertEquals(3, counter.get());

        oneProp.set(true);

        Assert.assertEquals(TestEnum.One, prop.get());
        Assert.assertTrue(oneProp.get());
        Assert.assertFalse(twoProp.get());
        Assert.assertFalse(threeProp.get());
        Assert.assertEquals(4, counter.get());

        threeProp.set(true);

        Assert.assertEquals(TestEnum.Three, prop.get());
        Assert.assertFalse(oneProp.get());
        Assert.assertFalse(twoProp.get());
        Assert.assertTrue(threeProp.get());
        Assert.assertEquals(6, counter.get());

        prop.set(TestEnum.Two);

        Assert.assertEquals(TestEnum.Two, prop.get());
        Assert.assertFalse(oneProp.get());
        Assert.assertTrue(twoProp.get());
        Assert.assertFalse(threeProp.get());
        Assert.assertEquals(9, counter.get());
    }
}