package org.pcsoft.framework.jfex.property;

import javafx.beans.property.Property;


public class CastWrapperProperty<Specific extends Base, Base> extends SimpleWrapperProperty<Specific, Base> {
    private final Class<Specific> specificClass;

    public CastWrapperProperty(Class<Specific> specificClass, Property<Base> property) {
        super(property);
        this.specificClass = specificClass;
    }

    @Override
    protected Specific convertTo(Base value) {
        return value == null ? null : !specificClass.isAssignableFrom(value.getClass()) ? null : (Specific)value;
    }

    @Override
    protected Base convertFrom(Specific value) {
        return value == null ? null : (Base)value;
    }
}
