package org.pcsoft.framework.jfex.ui.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.pcsoft.framework.jfex.property.SimpleWrapperProperty;
import org.pcsoft.framework.jfex.type.GroupItem;

import java.util.*;
import java.util.stream.Collectors;


public class GroupComboBox<T, G extends Comparable<G>> extends ComboBox<GroupComboBox.Item> {
    public static interface Item {
    }

    public static final class GroupValueItem<T> implements Item {
        private final T info;

        private GroupValueItem(T info) {
            this.info = info;
        }

        public T getInfo() {
            return info;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupValueItem<?> that = (GroupValueItem<?>) o;
            return Objects.equals(info, that.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(info);
        }
    }

    public static final class DataValueItem<T> implements Item {
        private final T value;

        private DataValueItem(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataValueItem<?> that = (DataValueItem<?>) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private ReadOnlyListProperty<GroupItem<T, G>> groupItems = new ReadOnlyListWrapper<GroupItem<T, G>>(FXCollections.observableArrayList()).getReadOnlyProperty();
    private ObjectProperty<T> rawValue = new SimpleWrapperProperty<T, Item>(valueProperty()) {
        @Override
        protected T convertTo(Item value) {
            return (value instanceof GroupComboBox.DataValueItem) ? ((DataValueItem<T>) value).value : null;
        }

        @Override
        protected Item convertFrom(T value) {
            return new DataValueItem<>(value);
        }
    };

    public GroupComboBox() {
        groupItems.addListener(new ListChangeListener<GroupItem<T, G>>() {
            @Override
            public void onChanged(Change<? extends GroupItem<T, G>> c) {
                final Map<G, List<GroupItem<T, G>>> groupedMap = groupItems.parallelStream()
                        .collect(Collectors.groupingBy(GroupItem::getGroupValue));

                final ArrayList<G> groupValueList = new ArrayList<>(groupedMap.keySet());
                Collections.sort(groupValueList);

                getItems().clear();
                for (final G groupValue : groupValueList) {
                    getItems().add(new GroupValueItem<>(groupValue));
                    getItems().addAll(
                            groupedMap.get(groupValue).parallelStream()
                                    .map(item -> new DataValueItem<>(item.getDataValue()))
                                    .collect(Collectors.toList())
                    );
                }
            }
        });
    }

    public ObservableList<GroupItem<T, G>> getGroupItems() {
        return groupItems.get();
    }

    public ReadOnlyListProperty<GroupItem<T, G>> groupItemsProperty() {
        return groupItems;
    }

    public Object getRawValue() {
        return rawValue.get();
    }

    public ObjectProperty<T> rawValueProperty() {
        return rawValue;
    }

    public void setRawValue(T rawValue) {
        this.rawValue.set(rawValue);
    }
}
