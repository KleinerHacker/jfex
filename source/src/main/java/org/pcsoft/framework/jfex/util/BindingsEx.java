package org.pcsoft.framework.jfex.util;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

public final class BindingsEx {
    //<editor-fold desc="Native Boolean">
    public static BooleanBinding or(BooleanBinding... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            current = props[i].or(current);
        }

        return current;
    }

    public static BooleanBinding or(BooleanProperty... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            current = props[i].or(current);
        }

        return current;
    }

    public static BooleanBinding and(BooleanBinding... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            current = props[i].and(current);
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

    public static BooleanBinding xor(BooleanBinding... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> props[finalI].get() ^ finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    public static BooleanBinding xor(BooleanProperty... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> props[0].get(), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> props[finalI].get() ^ finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    public static BooleanBinding not(BooleanBinding prop) {
        return prop.not();
    }

    public static BooleanBinding not(BooleanProperty prop) {
        return prop.not();
    }
    //</editor-fold>

    //<editor-fold desc="Object Boolean">
    @SafeVarargs
    public static BooleanBinding or(ObjectBinding<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> convertBoolean(props[0]), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> convertBoolean(props[finalI]) || finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    @SafeVarargs
    public static BooleanBinding or(ObjectProperty<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = convert(props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> convertBoolean(props[finalI]) || finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    @SafeVarargs
    public static BooleanBinding and(ObjectBinding<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = Bindings.createBooleanBinding(() -> convertBoolean(props[0]), props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> convertBoolean(props[finalI]) && finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    @SafeVarargs
    public static BooleanBinding and(ObjectProperty<Boolean>... props) {
        if (props.length <= 1)
            throw new IllegalArgumentException("Minimum 2 properties are needed");

        BooleanBinding current = convert(props[0]);
        for (int i = 1; i < props.length; i++) {
            final int finalI = i;
            final BooleanBinding finalCurrent = current;
            current = Bindings.createBooleanBinding(() -> convertBoolean(props[finalI]) && finalCurrent.get(), props[finalI], finalCurrent);
        }

        return current;
    }

    public static BooleanBinding not(ObjectProperty<Boolean> prop) {
        return convert(prop).not();
    }

    public static BooleanBinding convert(ObjectProperty<Boolean> prop) {
        return Bindings.createBooleanBinding(() -> convertBoolean(prop), prop);
    }
    //</editor-fold>

    private static boolean convertBoolean(ObjectBinding<Boolean> prop) {
        return prop.get() != null && prop.get();
    }

    private static boolean convertBoolean(ObjectProperty<Boolean> prop) {
        return prop.get() != null && prop.get();
    }

    private BindingsEx() {
    }
}
