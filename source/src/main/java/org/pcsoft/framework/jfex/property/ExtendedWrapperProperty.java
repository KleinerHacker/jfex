package org.pcsoft.framework.jfex.property;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.Collection;


public abstract class ExtendedWrapperProperty<T> extends SimpleObjectProperty<T> {

    public ExtendedWrapperProperty(Observable... observables) {
        this(null, observables);
    }

    public ExtendedWrapperProperty(T value, Observable... observables) {
        this(value, Arrays.asList(observables));
    }

    public ExtendedWrapperProperty(Collection<Observable> observables) {
        this(null, observables);
    }

    public ExtendedWrapperProperty(T value, Collection<Observable> observables) {
        super(value);
        for (final Observable observable : observables) {
            observable.addListener(o -> {
                fireValueChangedEvent();
                onValueChange(o);
            });
        }
    }

    @Override
    public final T get() {
        return this.getValue();
    }

    @Override
    public final void set(T newValue) {
        this.setValue(newValue);
    }

    @Override
    public final void setValue(T v) {
        setPseudoValue(v);
        fireValueChangedEvent();
    }

    @Override
    public final T getValue() {
        return getPseudoValue();
    }

    protected abstract T getPseudoValue();
    protected abstract void setPseudoValue(T value);

    protected void onValueChange(final Observable observable) {
        //Do nothing, only for overwriting
    }

    @Override
    protected final void fireValueChangedEvent() {
        super.fireValueChangedEvent();
    }
}
