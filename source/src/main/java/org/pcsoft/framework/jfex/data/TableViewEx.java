//package org.pcsoft.framework.jfex.data;
//
//import de.saxsys.mvvmfx.FluentViewLoader;
//import de.saxsys.mvvmfx.ViewModel;
//import de.saxsys.mvvmfx.ViewTuple;
//import de.saxsys.mvvmfx.internal.viewloader.View;
//import javafx.beans.property.ListProperty;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.ReadOnlyListProperty;
//import javafx.beans.property.StringProperty;
//import javafx.collections.ObservableList;
//import javafx.scene.control.MultipleSelectionModel;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableRow;
//import javafx.scene.layout.StackPane;
//
//import java.util.Comparator;
//import java.util.ResourceBundle;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//public class TableViewEx<T, G> extends StackPane implements MultipleSelectionDataModel<T, G, TableRow> {
//    private final TableViewExView<T, G> view;
//    private final View<? extends ViewModel> controller;
//    private final TableViewExViewModel<T, G> viewModel;
//
//    public TableViewEx() {
//        final ViewTuple<?, ViewModel> viewTuple =
//                FluentViewLoader.fxmlView(TableViewExView.class).resourceBundle(ResourceBundle.getBundle("lan/text")).root(this).load();
//        controller = viewTuple.getCodeBehind();
//
//        viewModel = (TableViewExViewModel<T, G>) viewTuple.getViewModel();
//        view = (TableViewExView<T, G>) viewTuple.getCodeBehind();
//    }
//
//    public ObservableList<TableColumn<T, ?>> getColumns() {
//        return viewModel.getColumns();
//    }
//
//    @Override
//    public ObservableList<T> getFilteredItems() {
//        return viewModel.getFilteredItems();
//    }
//
//    @Override
//    public ReadOnlyListProperty<T> filteredItemsProperty() {
//        return viewModel.filteredItemsProperty();
//    }
//
//    @Override
//    public BiFunction<T, Object, Boolean> getFilterCallback() {
//        return viewModel.getFilterCallback();
//    }
//
//    @Override
//    public ObjectProperty<BiFunction<T, Object, Boolean>> filterCallbackProperty() {
//        return viewModel.filterCallbackProperty();
//    }
//
//    @Override
//    public void setFilterCallback(BiFunction<T, Object, Boolean> filterCallback) {
//        viewModel.setFilterCallback(filterCallback);
//    }
//
//    @Override
//    public Object getFilterValue() {
//        return viewModel.getFilterValue();
//    }
//
//    @Override
//    public ObjectProperty<Object> filterValueProperty() {
//        return viewModel.filterValueProperty();
//    }
//
//    @Override
//    public void setFilterValue(Object filterValue) {
//        viewModel.setFilterValue(filterValue);
//    }
//
//    @Override
//    public String getLoadingText() {
//        return viewModel.getLoadingText();
//    }
//
//    @Override
//    public StringProperty loadingTextProperty() {
//        return viewModel.loadingTextProperty();
//    }
//
//    @Override
//    public void setLoadingText(String loadingText) {
//        viewModel.setLoadingText(loadingText);
//    }
//
//    @Override
//    public ItemLoader<T> getItemLoader() {
//        return viewModel.getItemLoader();
//    }
//
//    @Override
//    public ObjectProperty<ItemLoader<T>> itemLoaderProperty() {
//        return viewModel.itemLoaderProperty();
//    }
//
//    @Override
//    public void setItemLoader(ItemLoader<T> itemLoader) {
//        viewModel.setItemLoader(itemLoader);
//    }
//
//    @Override
//    public Comparator<T> getValueComparator() {
//        return viewModel.getValueComparator();
//    }
//
//    @Override
//    public ObjectProperty<Comparator<T>> valueComparatorProperty() {
//        return viewModel.valueComparatorProperty();
//    }
//
//    @Override
//    public void setValueComparator(Comparator<T> valueComparator) {
//        viewModel.setValueComparator(valueComparator);
//    }
//
//    @Override
//    public Comparator<G> getHeaderComparator() {
//        return viewModel.getHeaderComparator();
//    }
//
//    @Override
//    public ObjectProperty<Comparator<G>> headerComparatorProperty() {
//        return viewModel.headerComparatorProperty();
//    }
//
//    @Override
//    public void setHeaderComparator(Comparator<G> headerComparator) {
//        viewModel.setHeaderComparator(headerComparator);
//    }
//
//    @Override
//    public Function<T, G> getHeaderKeyExtractor() {
//        return viewModel.getHeaderKeyExtractor();
//    }
//
//    @Override
//    public ObjectProperty<Function<T, G>> headerKeyExtractorProperty() {
//        return viewModel.headerKeyExtractorProperty();
//    }
//
//    @Override
//    public void setHeaderKeyExtractor(Function<T, G> headerKeyExtractor) {
//        viewModel.setHeaderKeyExtractor(headerKeyExtractor);
//    }
//
//    @Override
//    public ObservableList<T> getItems() {
//        return viewModel.getItems();
//    }
//
//    @Override
//    public ListProperty<T> itemsProperty() {
//        return viewModel.itemsProperty();
//    }
//
//    @Override
//    public void setItems(ObservableList<T> items) {
//        viewModel.setItems(items);
//    }
//
//    @Override
//    public CellRendererCallback<TableRow, T> getValueCellRendererCallback() {
//        return viewModel.getValueCellRendererCallback();
//    }
//
//    @Override
//    public ObjectProperty<CellRendererCallback<TableRow, T>> valueCellRendererCallbackProperty() {
//        return viewModel.valueCellRendererCallbackProperty();
//    }
//
//    @Override
//    public void setValueCellRendererCallback(CellRendererCallback<TableRow, T> valueCellRendererCallback) {
//        viewModel.setValueCellRendererCallback(valueCellRendererCallback);
//    }
//
//    @Override
//    public CellRendererCallback<TableRow, G> getHeaderCellRendererCallback() {
//        return viewModel.getHeaderCellRendererCallback();
//    }
//
//    @Override
//    public ObjectProperty<CellRendererCallback<TableRow, G>> headerCellRendererCallbackProperty() {
//        return viewModel.headerCellRendererCallbackProperty();
//    }
//
//    @Override
//    public void setHeaderCellRendererCallback(CellRendererCallback<TableRow, G> headerCellRendererCallback) {
//        viewModel.setHeaderCellRendererCallback(headerCellRendererCallback);
//    }
//
//    @Override
//    public MultipleSelectionModel<T> getSelectionModel() {
//        return viewModel.getSelectionModel();
//    }
//
//    @Override
//    public ObjectProperty<MultipleSelectionModel<T>> selectionModelProperty() {
//        return viewModel.selectionModelProperty();
//    }
//
//    @Override
//    public void setSelectionModel(MultipleSelectionModel<T> selectionModel) {
//        viewModel.setSelectionModel(selectionModel);
//    }
//}
