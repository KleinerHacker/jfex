package org.pcsoft.framework.jfex.property;

import javafx.beans.property.Property;


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
