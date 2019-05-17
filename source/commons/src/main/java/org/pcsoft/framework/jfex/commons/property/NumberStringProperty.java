package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.Property;
import org.pcsoft.framework.jfex.commons.util.NumberConversionUtils;


public class NumberStringProperty<T extends Number> extends SimpleWrapperProperty<String, T> {

    private final Class<T> clazz;

    public NumberStringProperty(Property<T> value, Class<T> clazz) {
        super(value);
        this.clazz = clazz;

        value.addListener((v, o, n) -> fireValueChangedEvent());
    }

    @Override
    protected String convertTo(T value) {
        return NumberConversionUtils.convertFromNumber(value);
    }

    @Override
    protected T convertFrom(String value) {
        return NumberConversionUtils.convertToNumber(value, clazz);
    }
}
