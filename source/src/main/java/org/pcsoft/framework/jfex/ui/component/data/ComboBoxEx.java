package org.pcsoft.framework.jfex.ui.component.data;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.internal.viewloader.View;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.listener.ProgressListener;

import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;


public class ComboBoxEx<T, G> extends StackPane implements SimpleDataModel<T, G, ListCell> {
    private final ComboBoxExView<T, G> view;
    private final View<? extends ViewModel> controller;
    private final ComboBoxExViewModel<T, G> viewModel;

    public ComboBoxEx() {
        final ViewTuple<?, ViewModel> viewTuple =
                FluentViewLoader.fxmlView(ComboBoxExView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        controller = viewTuple.getCodeBehind();

        viewModel = (ComboBoxExViewModel<T, G>) viewTuple.getViewModel();
        view = (ComboBoxExView<T, G>) viewTuple.getCodeBehind();
    }

    @Override
    public ObservableList<T> getFilteredItems() {
        return viewModel.getFilteredItems();
    }

    @Override
    public ReadOnlyListProperty<T> filteredItemsProperty() {
        return viewModel.filteredItemsProperty();
    }

    @Override
    public BiFunction<T, Object, Boolean> getFilterCallback() {
        return viewModel.getFilterCallback();
    }

    @Override
    public ObjectProperty<BiFunction<T, Object, Boolean>> filterCallbackProperty() {
        return viewModel.filterCallbackProperty();
    }

    @Override
    public void setFilterCallback(BiFunction<T, Object, Boolean> filterCallback) {
        viewModel.setFilterCallback(filterCallback);
    }

    @Override
    public Object getFilterValue() {
        return viewModel.getFilterValue();
    }

    @Override
    public ObjectProperty<Object> filterValueProperty() {
        return viewModel.filterValueProperty();
    }

    @Override
    public void setFilterValue(Object filterValue) {
        viewModel.setFilterValue(filterValue);
    }

    @Override
    public String getLoadingText() {
        return viewModel.getLoadingText();
    }

    @Override
    public StringProperty loadingTextProperty() {
        return viewModel.loadingTextProperty();
    }

    @Override
    public void setLoadingText(String loadingText) {
        viewModel.setLoadingText(loadingText);
    }

    @Override
    public ItemLoader<T> getItemLoader() {
        return viewModel.getItemLoader();
    }

    @Override
    public ObjectProperty<ItemLoader<T>> itemLoaderProperty() {
        return viewModel.itemLoaderProperty();
    }

    @Override
    public void setItemLoader(ItemLoader<T> itemLoader) {
        viewModel.setItemLoader(itemLoader);
    }

    @Override
    public Comparator<T> getValueComparator() {
        return viewModel.getValueComparator();
    }

    @Override
    public ObjectProperty<Comparator<T>> valueComparatorProperty() {
        return viewModel.valueComparatorProperty();
    }

    @Override
    public void setValueComparator(Comparator<T> valueComparator) {
        viewModel.setValueComparator(valueComparator);
    }

    @Override
    public Comparator<G> getHeaderComparator() {
        return viewModel.getHeaderComparator();
    }

    @Override
    public ObjectProperty<Comparator<G>> headerComparatorProperty() {
        return viewModel.headerComparatorProperty();
    }

    @Override
    public void setHeaderComparator(Comparator<G> headerComparator) {
        viewModel.setHeaderComparator(headerComparator);
    }

    @Override
    public Function<T, G> getHeaderKeyExtractor() {
        return viewModel.getHeaderKeyExtractor();
    }

    @Override
    public ObjectProperty<Function<T, G>> headerKeyExtractorProperty() {
        return viewModel.headerKeyExtractorProperty();
    }

    @Override
    public void setHeaderKeyExtractor(Function<T, G> headerKeyExtractor) {
        viewModel.setHeaderKeyExtractor(headerKeyExtractor);
    }

    public T getValue() {
        return viewModel.getValue();
    }

    public ObjectProperty<T> valueProperty() {
        return viewModel.valueProperty();
    }

    public void setValue(T value) {
        viewModel.setValue(value);
    }

    @Override
    public ObservableList<T> getItems() {
        return viewModel.getItems();
    }

    @Override
    public ListProperty<T> itemsProperty() {
        return viewModel.itemsProperty();
    }

    @Override
    public void setItems(ObservableList<T> items) {
        viewModel.setItems(items);
    }

    @Override
    public CellRendererCallback<ListCell, T> getValueCellRendererCallback() {
        return viewModel.getValueCellRendererCallback();
    }

    @Override
    public ObjectProperty<CellRendererCallback<ListCell, T>> valueCellRendererCallbackProperty() {
        return viewModel.valueCellRendererCallbackProperty();
    }

    @Override
    public void setValueCellRendererCallback(CellRendererCallback<ListCell, T> valueCellRendererCallback) {
        viewModel.setValueCellRendererCallback(valueCellRendererCallback);
    }

    public CellRendererCallback<ListCell, T> getValueButtonCellRendererCallback() {
        return viewModel.getValueButtonCellRendererCallback();
    }

    public ObjectProperty<CellRendererCallback<ListCell, T>> valueButtonCellRendererCallbackProperty() {
        return viewModel.valueButtonCellRendererCallbackProperty();
    }

    public void setValueButtonCellRendererCallback(CellRendererCallback<ListCell, T> valueButtonCellRendererCallback) {
        viewModel.setValueButtonCellRendererCallback(valueButtonCellRendererCallback);
    }

    @Override
    public CellRendererCallback<ListCell, G> getHeaderCellRendererCallback() {
        return viewModel.getHeaderCellRendererCallback();
    }

    @Override
    public ObjectProperty<CellRendererCallback<ListCell, G>> headerCellRendererCallbackProperty() {
        return viewModel.headerCellRendererCallbackProperty();
    }

    @Override
    public void setHeaderCellRendererCallback(CellRendererCallback<ListCell, G> headerCellRendererCallback) {
        viewModel.setHeaderCellRendererCallback(headerCellRendererCallback);
    }

    @Override
    public CellRendererCallback<ListCell, Void> getEmptyCellRendererCallback() {
        return viewModel.getEmptyCellRendererCallback();
    }

    @Override
    public ObjectProperty<CellRendererCallback<ListCell, Void>> emptyCellRendererCallbackProperty() {
        return viewModel.emptyCellRendererCallbackProperty();
    }

    @Override
    public void setEmptyCellRendererCallback(CellRendererCallback<ListCell, Void> emptyCellRendererCallback) {
        viewModel.setEmptyCellRendererCallback(emptyCellRendererCallback);
    }

    @Override
    public ProgressListener getProgressListener() {
        return viewModel.getProgressListener();
    }

    @Override
    public ObjectProperty<ProgressListener> progressListenerProperty() {
        return viewModel.progressListenerProperty();
    }

    @Override
    public void setProgressListener(ProgressListener progressListener) {
        viewModel.setProgressListener(progressListener);
    }

    public String getPromptText() {
        return viewModel.getPromptText();
    }

    public StringProperty promptTextProperty() {
        return viewModel.promptTextProperty();
    }

    public void setPromptText(String prompt) {
        viewModel.setPromptText(prompt);
    }

    public void refresh() {
        view.refresh(true);
    }

    @Override
    public Runnable getOnItemsLoaded() {
        return view.getOnItemsLoaded();
    }

    @Override
    public ObjectProperty<Runnable> onItemsLoadedProperty() {
        return view.onItemsLoadedProperty();
    }

    @Override
    public void setOnItemsLoaded(Runnable onItemsLoaded) {
        view.setOnItemsLoaded(onItemsLoaded);
    }

    @Override
    public Runnable getOnItemsLoading() {
        return view.getOnItemsLoading();
    }

    @Override
    public ObjectProperty<Runnable> onItemsLoadingProperty() {
        return view.onItemsLoadingProperty();
    }

    @Override
    public void setOnItemsLoading(Runnable onItemsLoading) {
        view.setOnItemsLoading(onItemsLoading);
    }
}
