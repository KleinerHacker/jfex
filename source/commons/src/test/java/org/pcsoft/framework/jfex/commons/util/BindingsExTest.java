package org.pcsoft.framework.jfex.commons.util;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jfex.commons.util.BindingsEx;

public class BindingsExTest {

    @Test
    public void testOrSimple() {
        final BooleanProperty v1 = new SimpleBooleanProperty(), v2 = new SimpleBooleanProperty(), v3 = new SimpleBooleanProperty();
        final BooleanBinding or = BindingsEx.or(v1, v2, v3);

        Assert.assertFalse(or.get());

        v1.set(true);
        Assert.assertTrue(or.get());

        v2.set(true);
        v3.set(true);
        Assert.assertTrue(or.get());
    }

    @Test
    public void testOrObject() {
        final ObjectProperty<Boolean> v1 = new SimpleObjectProperty<>(), v2 = new SimpleObjectProperty<>(), v3 = new SimpleObjectProperty<>();
        final BooleanBinding or = BindingsEx.or(v1, v2, v3);

        Assert.assertFalse(or.get());

        v1.set(true);
        Assert.assertTrue(or.get());

        v2.set(true);
        v3.set(true);
        Assert.assertTrue(or.get());

        v1.set(false);
        v2.set(false);
        v3.set(false);
        Assert.assertFalse(or.get());
    }

    @Test
    public void testAndSimple() {
        final BooleanProperty v1 = new SimpleBooleanProperty(), v2 = new SimpleBooleanProperty(), v3 = new SimpleBooleanProperty();
        final BooleanBinding and = BindingsEx.and(v1, v2, v3);

        Assert.assertFalse(and.get());

        v1.set(true);
        Assert.assertFalse(and.get());

        v2.set(true);
        v3.set(true);
        Assert.assertTrue(and.get());
    }

    @Test
    public void testAndObject() {
        final ObjectProperty<Boolean> v1 = new SimpleObjectProperty<>(), v2 = new SimpleObjectProperty<>(), v3 = new SimpleObjectProperty<>();
        final BooleanBinding and = BindingsEx.and(v1, v2, v3);

        Assert.assertFalse(and.get());

        v1.set(true);
        Assert.assertFalse(and.get());

        v2.set(true);
        v3.set(true);
        Assert.assertTrue(and.get());

        v1.set(false);
        Assert.assertFalse(and.get());

        v1.set(false);
        v2.set(false);
        v3.set(false);
        Assert.assertFalse(and.get());
    }

    @Test
    public void testNotSimple() {
        final BooleanProperty v1 = new SimpleBooleanProperty();
        final BooleanBinding not = BindingsEx.not(v1);

        Assert.assertTrue(not.get());

        v1.set(true);
        Assert.assertFalse(not.get());
    }

    @Test
    public void testNotObject() {
        final ObjectProperty<Boolean> v1 = new SimpleObjectProperty<>();
        final BooleanBinding not = BindingsEx.not(v1);

        Assert.assertTrue(not.get());

        v1.set(true);
        Assert.assertFalse(not.get());

        v1.set(false);
        Assert.assertTrue(not.get());
    }
}