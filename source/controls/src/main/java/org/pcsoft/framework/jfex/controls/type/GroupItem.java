package org.pcsoft.framework.jfex.controls.type;

import java.util.Objects;


public final class GroupItem<T,G extends Comparable<G>> {
    private final T dataValue;
    private final G groupValue;

    public GroupItem(T dataValue, G groupValue) {
        this.dataValue = dataValue;
        this.groupValue = groupValue;
    }

    public T getDataValue() {
        return dataValue;
    }

    public G getGroupValue() {
        return groupValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupItem<?, ?> groupItem = (GroupItem<?, ?>) o;
        return Objects.equals(dataValue, groupItem.dataValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataValue);
    }
}
