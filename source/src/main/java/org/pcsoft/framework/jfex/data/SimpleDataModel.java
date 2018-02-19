package org.pcsoft.framework.jfex.data;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.IndexedCell;
import org.pcsoft.framework.jfex.listener.ProgressListener;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;


public interface SimpleDataModel<T, G, C extends IndexedCell> {
    ObservableList<T> getFilteredItems();

    ReadOnlyListProperty<T> filteredItemsProperty();

    BiFunction<T, Object, Boolean> getFilterCallback();

    ObjectProperty<BiFunction<T, Object, Boolean>> filterCallbackProperty();

    void setFilterCallback(BiFunction<T, Object, Boolean> filterCallback);

    Object getFilterValue();

    ObjectProperty<Object> filterValueProperty();

    void setFilterValue(Object filterValue);

    String getLoadingText();

    StringProperty loadingTextProperty();

    void setLoadingText(String loadingText);

    ItemLoader<T> getItemLoader();

    ObjectProperty<ItemLoader<T>> itemLoaderProperty();

    void setItemLoader(ItemLoader<T> itemLoader);

    Comparator<T> getValueComparator();

    ObjectProperty<Comparator<T>> valueComparatorProperty();

    void setValueComparator(Comparator<T> valueComparator);

    Comparator<G> getHeaderComparator();

    ObjectProperty<Comparator<G>> headerComparatorProperty();

    void setHeaderComparator(Comparator<G> headerComparator);

    Function<T, G> getHeaderKeyExtractor();

    ObjectProperty<Function<T, G>> headerKeyExtractorProperty();

    void setHeaderKeyExtractor(Function<T, G> headerKeyExtractor);

    ObservableList<T> getItems();

    ListProperty<T> itemsProperty();

    void setItems(ObservableList<T> items);

    CellRendererCallback<C, T> getValueCellRendererCallback();

    ObjectProperty<CellRendererCallback<C, T>> valueCellRendererCallbackProperty();

    void setValueCellRendererCallback(CellRendererCallback<C, T> valueCellRendererCallback);

    CellRendererCallback<C, G> getHeaderCellRendererCallback();

    ObjectProperty<CellRendererCallback<C, G>> headerCellRendererCallbackProperty();

    void setHeaderCellRendererCallback(CellRendererCallback<C, G> headerCellRendererCallback);

    CellRendererCallback<C, Void> getEmptyCellRendererCallback();

    ObjectProperty<CellRendererCallback<C, Void>> emptyCellRendererCallbackProperty();

    void setEmptyCellRendererCallback(CellRendererCallback<C, Void> emptyCellRendererCallback);

    ProgressListener getProgressListener();

    ObjectProperty<ProgressListener> progressListenerProperty();

    void setProgressListener(ProgressListener progressListener);
}
