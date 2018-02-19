package org.pcsoft.framework.jfex.data;


import org.pcsoft.framework.jfex.type.MultipleSelectionModelWrapper;


final class DataSelectionModelWrapper<T> extends MultipleSelectionModelWrapper<T, Item> {
    @Override
    protected T convertFrom(Item item) {
        return item == null ? null : (T) item.getData();
    }

    @Override
    protected Item convertTo(T t) {
        return t == null ? null : new ItemValue<>(t);
    }
}
