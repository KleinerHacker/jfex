package org.pcsoft.framework.jfex.util;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

public final class BindingsEx {
    public static BooleanBinding or(BooleanProperty... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            current = props[i].or(current);
        }

        return current;
    }

    public static BooleanBinding and(BooleanProperty... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            current = props[i].and(current);
        }

        return current;
    }

    public static BooleanBinding or(ObjectProperty<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> props[finalI].get() || finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    @SafeVarargs
    public static BooleanBinding and(ObjectProperty<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> props[finalI].get() && finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    private BindingsEx() {
    }
}
