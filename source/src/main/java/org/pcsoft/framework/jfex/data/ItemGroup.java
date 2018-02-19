package org.pcsoft.framework.jfex.data;

import java.util.Objects;


final class ItemGroup<T> implements Item<T> {
    private final T data;

    public ItemGroup(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroup<?> itemGroup = (ItemGroup<?>) o;
        return Objects.equals(data, itemGroup.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data == null ? "null" : data.toString();
    }
}
