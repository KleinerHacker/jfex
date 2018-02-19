package org.pcsoft.framework.jfex.data;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.internal.viewloader.View;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.StackPane;
import org.pcsoft.framework.jfex.listener.ProgressListener;

import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;


public class ListViewEx<T, G> extends StackPane implements MultipleSelectionDataModel<T, G, ListCell> {
    public enum ToolbarOrientation {
        TOP(Orientation.HORIZONTAL),
        BOTTOM(Orientation.HORIZONTAL),
        LEFT(Orientation.VERTICAL),
        RIGHT(Orientation.VERTICAL);

        private final Orientation orientation;

        private ToolbarOrientation(Orientation orientation) {
            this.orientation = orientation;
        }

        public Orientation getOrientation() {
            return orientation;
        }
    }

    private final ListViewExView<T, G> view;
    private final View<? extends ViewModel> controller;
    private final ListViewExViewModel<T, G> viewModel;

    public ListViewEx() {
        final ViewTuple<?, ViewModel> viewTuple =
                FluentViewLoader.fxmlView(ListViewExView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
        controller = viewTuple.getCodeBehind();

        viewModel = (ListViewExViewModel<T, G>) viewTuple.getViewModel();
        view = (ListViewExView<T, G>) viewTuple.getCodeBehind();
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
    public MultipleSelectionModel<T> getSelectionModel() {
        return viewModel.getSelectionModel();
    }

    @Override
    public ObjectProperty<MultipleSelectionModel<T>> selectionModelProperty() {
        return viewModel.selectionModelProperty();
    }

    @Override
    public void setSelectionModel(MultipleSelectionModel<T> selectionModel) {
        viewModel.setSelectionModel(selectionModel);
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

    public String getEmptyText() {
        return viewModel.getEmptyText();
    }

    public StringProperty emptyTextProperty() {
        return viewModel.emptyTextProperty();
    }

    public void setEmptyText(String emptyText) {
        viewModel.setEmptyText(emptyText);
    }

    public String getEmptyTextStyle() {
        return viewModel.getEmptyTextStyle();
    }

    public StringProperty emptyTextStyleProperty() {
        return viewModel.emptyTextStyleProperty();
    }

    /**
     * A string representation of the CSS style associated with this
     * specific {@code Node}. This is analogous to the "style" attribute of an
     * HTML element. Note that, like the HTML style attribute, this
     * variable contains style properties and values and not the
     * selector portion of a style rule.
     * @param value The inline CSS style to use for this {@code Node}.
     *         {@code null} is implicitly converted to an empty String.
     * @defaultValue empty string
     * @see <a href="doc-files/cssref.html">CSS Reference Guide</a>.
     */
    public void setEmptyTextStyle(String value) {
        viewModel.setEmptyTextStyle(value);
    }

    public EventHandler<ListItemClickEvent<T>> getOnItemClick() {
        return viewModel.getOnItemClick();
    }

    public ObjectProperty<EventHandler<ListItemClickEvent<T>>> onItemClickProperty() {
        return viewModel.onItemClickProperty();
    }

    public void setOnItemClick(EventHandler<ListItemClickEvent<T>> onItemClick) {
        viewModel.setOnItemClick(onItemClick);
    }

    public ToolbarOrientation getOrientation() {
        return viewModel.getOrientation();
    }

    public ObjectProperty<ToolbarOrientation> orientationProperty() {
        return viewModel.orientationProperty();
    }

    public void setOrientation(ToolbarOrientation orientation) {
        viewModel.setOrientation(orientation);
    }

    public ObservableList<Node> getToolbarItems() {
        return viewModel.getToolbarItems();
    }

    public ReadOnlyListProperty<Node> toolbarItemsProperty() {
        return viewModel.toolbarItemsProperty();
    }

    public boolean isShowToolbar() {
        return viewModel.isShowToolbar();
    }

    public BooleanProperty showToolbarProperty() {
        return viewModel.showToolbarProperty();
    }

    public void setShowToolbar(boolean showToolbar) {
        viewModel.setShowToolbar(showToolbar);
    }

    public void refresh() {
        view.refresh(true);
    }
}
