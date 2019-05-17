package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Extended wrapper property
 * @param <T>
 *
 * @deprecated Please use {@link org.pcsoft.framework.jfex.commons.util.Properties#createProperty(Supplier, Consumer, Observable...)} instead
 */
@Deprecated(since = "3.0.0")
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
