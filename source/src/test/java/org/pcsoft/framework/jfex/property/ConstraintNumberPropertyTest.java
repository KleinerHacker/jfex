package org.pcsoft.framework.jfex.property;

import javafx.beans.property.ObjectProperty;
import org.junit.Assert;
import org.junit.Test;


public class ConstraintNumberPropertyTest {

    @Test
    public void testMinimum() {
        final ObjectProperty<Integer> prop = new ConstraintNumberProperty<>(new ConstraintNumberProperty.MinimumConstraint<>(-10));
        Assert.assertNull(prop.get());

        prop.set(5);
        Assert.assertEquals(5, prop.get().intValue());

        prop.set(-5);
        Assert.assertEquals(-5, prop.get().intValue());

        prop.set(-45);
        Assert.assertEquals(-10, prop.get().intValue());

        prop.set(45);
        Assert.assertEquals(45, prop.get().intValue());
    }

    @Test
    public void testMaximum() {
        final ObjectProperty<Integer> prop = new ConstraintNumberProperty<>(new ConstraintNumberProperty.MaximumConstraint<>(10));
        Assert.assertNull(prop.get());

        prop.set(-5);
        Assert.assertEquals(-5, prop.get().intValue());

        prop.set(5);
        Assert.assertEquals(5, prop.get().intValue());

        prop.set(45);
        Assert.assertEquals(10, prop.get().intValue());

        prop.set(-45);
        Assert.assertEquals(-45, prop.get().intValue());
    }

    @Test
    public void testRange() {
        final ObjectProperty<Integer> prop = new ConstraintNumberProperty<>(new ConstraintNumberProperty.RangeConstraint<>(-10, 10));
        Assert.assertNull(prop.get());

        prop.set(5);
        Assert.assertEquals(5, prop.get().intValue());

        prop.set(-5);
        Assert.assertEquals(-5, prop.get().intValue());

        prop.set(-45);
        Assert.assertEquals(-10, prop.get().intValue());

        prop.set(45);
        Assert.assertEquals(10, prop.get().intValue());
    }

    @Test
    public void testNearestNumber() {
        final ObjectProperty<Integer> prop = new ConstraintNumberProperty<>(new ConstraintNumberProperty.NearestNumberConstraint<>(2, 5, 10));
        Assert.assertNull(prop.get());

        prop.set(5);
        Assert.assertEquals(5, prop.get().intValue());

        prop.set(-5);
        Assert.assertEquals(2, prop.get().intValue());

        prop.set(45);
        Assert.assertEquals(10, prop.get().intValue());

        prop.set(7);
        Assert.assertEquals(5, prop.get().intValue());

        prop.set(8);
        Assert.assertEquals(10, prop.get().intValue());
    }

}