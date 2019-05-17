package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.SimpleObjectProperty;

import java.util.function.Function;


public class ConstraintProperty<T> extends SimpleObjectProperty<T> {
    private final Function<T, T> constraint;

    public ConstraintProperty(Function<T, T> constraint) {
        super();
        this.constraint = constraint;
    }

    public ConstraintProperty(Function<T, T> constraint, T value) {
        super(value);
        this.constraint = constraint;
    }

    @Override
    public T get() {
        return constraint.apply(super.get());
    }

    @Override
    public void set(T newValue) {
        super.set(constraint.apply(newValue));
    }

    @Override
    public void setValue(T v) {
        super.setValue(constraint.apply(v));
    }

    @Override
    public T getValue() {
        return constraint.apply(super.getValue());
    }
}
