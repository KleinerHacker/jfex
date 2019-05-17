package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.IndexedCell;
import org.pcsoft.framework.jfex.controls.listener.ProgressListener;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;


public abstract class SimpleDataViewModel<T, G, C extends IndexedCell> implements FxmlViewModel, SimpleDataModel<T, G, C> {
    private final ListProperty<T> items = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ReadOnlyListProperty<T> filteredItems = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<ItemLoader<T>> itemLoader = new SimpleObjectProperty<>();
    private final StringProperty loadingText = new SimpleStringProperty("Loading...");
    private final ObjectProperty<ProgressListener> progressListener = new SimpleObjectProperty<>();

    private final ObjectProperty<BiFunction<T, Object, Boolean>> filterCallback = new SimpleObjectProperty<>();
    private final ObjectProperty<Object> filterValue = new SimpleObjectProperty<>();

    private final ObjectProperty<CellRendererCallback<C, T>> valueCellRendererCallback = new SimpleObjectProperty<>();
    private final ObjectProperty<Comparator<T>> valueComparator = new SimpleObjectProperty<>();

    private final ObjectProperty<CellRendererCallback<C, G>> headerCellRendererCallback = new SimpleObjectProperty<>();
    private final ObjectProperty<Function<T, G>> headerKeyExtractor = new SimpleObjectProperty<>();
    private final ObjectProperty<Comparator<G>> headerComparator = new SimpleObjectProperty<>();

    private final ObjectProperty<CellRendererCallback<C, Void>> emptyCellRendererCallback = new SimpleObjectProperty<>();

    private final ObjectProperty<Runnable> onItemsLoaded = new SimpleObjectProperty<>();
    private final ObjectProperty<Runnable> onItemsLoading = new SimpleObjectProperty<>();

    @Override
    public ObservableList<T> getFilteredItems() {
        return filteredItems.get();
    }

    @Override
    public ReadOnlyListProperty<T> filteredItemsProperty() {
        return filteredItems;
    }

    @Override
    public BiFunction<T, Object, Boolean> getFilterCallback() {
        return filterCallback.get();
    }

    @Override
    public ObjectProperty<BiFunction<T, Object, Boolean>> filterCallbackProperty() {
        return filterCallback;
    }

    @Override
    public void setFilterCallback(BiFunction<T, Object, Boolean> filterCallback) {
        this.filterCallback.set(filterCallback);
    }

    @Override
    public Object getFilterValue() {
        return filterValue.get();
    }

    @Override
    public ObjectProperty<Object> filterValueProperty() {
        return filterValue;
    }

    @Override
    public void setFilterValue(Object filterValue) {
        this.filterValue.set(filterValue);
    }

    @Override
    public String getLoadingText() {
        return loadingText.get();
    }

    @Override
    public StringProperty loadingTextProperty() {
        return loadingText;
    }

    @Override
    public void setLoadingText(String loadingText) {
        this.loadingText.set(loadingText);
    }

    @Override
    public ItemLoader<T> getItemLoader() {
        return itemLoader.get();
    }

    @Override
    public ObjectProperty<ItemLoader<T>> itemLoaderProperty() {
        return itemLoader;
    }

    @Override
    public void setItemLoader(ItemLoader<T> itemLoader) {
        this.itemLoader.set(itemLoader);
    }

    @Override
    public Comparator<T> getValueComparator() {
        return valueComparator.get();
    }

    @Override
    public ObjectProperty<Comparator<T>> valueComparatorProperty() {
        return valueComparator;
    }

    @Override
    public void setValueComparator(Comparator<T> valueComparator) {
        this.valueComparator.set(valueComparator);
    }

    @Override
    public Comparator<G> getHeaderComparator() {
        return headerComparator.get();
    }

    @Override
    public ObjectProperty<Comparator<G>> headerComparatorProperty() {
        return headerComparator;
    }

    @Override
    public void setHeaderComparator(Comparator<G> headerComparator) {
        this.headerComparator.set(headerComparator);
    }

    @Override
    public Function<T, G> getHeaderKeyExtractor() {
        return headerKeyExtractor.get();
    }

    @Override
    public ObjectProperty<Function<T, G>> headerKeyExtractorProperty() {
        return headerKeyExtractor;
    }

    @Override
    public void setHeaderKeyExtractor(Function<T, G> headerKeyExtractor) {
        this.headerKeyExtractor.set(headerKeyExtractor);
    }

    @Override
    public ObservableList<T> getItems() {
        return items.get();
    }

    @Override
    public ListProperty<T> itemsProperty() {
        return items;
    }

    @Override
    public void setItems(ObservableList<T> items) {
        this.items.set(items);
    }

    @Override
    public CellRendererCallback<C, T> getValueCellRendererCallback() {
        return valueCellRendererCallback.get();
    }

    @Override
    public ObjectProperty<CellRendererCallback<C, T>> valueCellRendererCallbackProperty() {
        return valueCellRendererCallback;
    }

    @Override
    public void setValueCellRendererCallback(CellRendererCallback<C, T> valueCellRendererCallback) {
        this.valueCellRendererCallback.set(valueCellRendererCallback);
    }

    @Override
    public CellRendererCallback<C, G> getHeaderCellRendererCallback() {
        return headerCellRendererCallback.get();
    }

    @Override
    public ObjectProperty<CellRendererCallback<C, G>> headerCellRendererCallbackProperty() {
        return headerCellRendererCallback;
    }

    @Override
    public void setHeaderCellRendererCallback(CellRendererCallback<C, G> headerCellRendererCallback) {
        this.headerCellRendererCallback.set(headerCellRendererCallback);
    }

    @Override
    public CellRendererCallback<C, Void> getEmptyCellRendererCallback() {
        return emptyCellRendererCallback.get();
    }

    @Override
    public ObjectProperty<CellRendererCallback<C, Void>> emptyCellRendererCallbackProperty() {
        return emptyCellRendererCallback;
    }

    @Override
    public void setEmptyCellRendererCallback(CellRendererCallback<C, Void> emptyCellRendererCallback) {
        this.emptyCellRendererCallback.set(emptyCellRendererCallback);
    }

    @Override
    public ProgressListener getProgressListener() {
        return progressListener.get();
    }

    @Override
    public ObjectProperty<ProgressListener> progressListenerProperty() {
        return progressListener;
    }

    @Override
    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener.set(progressListener);
    }

    @Override
    public Runnable getOnItemsLoaded() {
        return onItemsLoaded.get();
    }

    @Override
    public ObjectProperty<Runnable> onItemsLoadedProperty() {
        return onItemsLoaded;
    }

    @Override
    public void setOnItemsLoaded(Runnable onItemsLoaded) {
        this.onItemsLoaded.set(onItemsLoaded);
    }

    @Override
    public Runnable getOnItemsLoading() {
        return onItemsLoading.get();
    }

    @Override
    public ObjectProperty<Runnable> onItemsLoadingProperty() {
        return onItemsLoading;
    }

    @Override
    public void setOnItemsLoading(Runnable onItemsLoading) {
        this.onItemsLoading.set(onItemsLoading);
    }
}
