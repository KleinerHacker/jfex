package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.Property;

import java.util.function.Function;

/**
 * Simple wrapper property
 * @param <OuterType>
 * @param <InnerType>
 *
 * @deprecated Please use {@link org.pcsoft.framework.jfex.commons.util.Properties#createProperty(Property, Function, Function)} instead
 */
@Deprecated(since = "3.0.0")
public abstract class SimpleWrapperProperty<OuterType, InnerType> extends ExtendedWrapperProperty<OuterType> {
    private final Property<InnerType> property;

    public SimpleWrapperProperty(Property<InnerType> property) {
        super(property);
        this.property = property;
    }

    @Override
    protected final OuterType getPseudoValue() {
        if (property.getValue() == null)
            return null;

        return convertTo(property.getValue());
    }

    @Override
    protected final void setPseudoValue(OuterType value) {
        if (value == null) {
            property.setValue(null);
        } else {
            property.setValue(convertFrom(value));
        }
    }

    protected abstract OuterType convertTo(InnerType value);
    protected abstract InnerType convertFrom(OuterType value);
}
