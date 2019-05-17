package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.value.ObservableValue;


public abstract class ReadOnlySimpleWrapperProperty<T1, T2> extends ExtendedWrapperProperty<T1> {
    private final ObservableValue<T2> value;

    public ReadOnlySimpleWrapperProperty(ObservableValue<T2> observableValue) {
        super(observableValue);
        this.value = observableValue;
    }

    @Override
    protected final T1 getPseudoValue() {
        return convertFrom(this.value.getValue());
    }

    @Override
    protected final void setPseudoValue(T1 value) {
        convertTo(this.value.getValue(), value);
    }

    protected abstract T1 convertFrom(T2 value);
    protected void convertTo(T2 obj, T1 value) {
        //Empty
    }
}
