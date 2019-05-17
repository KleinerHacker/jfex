package org.pcsoft.framework.jfex.controls.type;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

import java.util.stream.Collectors;

/**
 * Multiple Selection Model to wrap from selection model of <code>WrappedType</code> to selection model of <code>Type</code>
 */
public abstract class MultipleSelectionModelWrapper<Type, WrapperType> extends MultipleSelectionModel<Type> {
    private MultipleSelectionModel<WrapperType> wrappedModel;

    private final ObservableList<Type> selectedItems = FXCollections.observableArrayList();
    private final ListChangeListener<WrapperType> listChangeListener = c -> this.selectedItems.setAll(wrappedModel.getSelectedItems().stream().map(this::convertFrom).collect(Collectors.toList()));
    private final ChangeListener<Number> indexChangeListener = (v, o, n) -> this.setSelectedIndex(n.intValue());
    private final ChangeListener<WrapperType> itemChangeListener = (v, o, n) -> this.setSelectedItem(convertFrom(n));

    public MultipleSelectionModelWrapper() {
    }

    public MultipleSelectionModelWrapper(MultipleSelectionModel<WrapperType> wrappedModel) {
        this();
        setWrappedModel(wrappedModel);
    }

    public MultipleSelectionModel<WrapperType> getWrappedModel() {
        return wrappedModel;
    }

    public void setWrappedModel(MultipleSelectionModel<WrapperType> wrappedModel) {
        if (this.wrappedModel != null) {
            this.wrappedModel.getSelectedItems().removeListener(listChangeListener);
            this.wrappedModel.selectedIndexProperty().removeListener(indexChangeListener);
            this.wrappedModel.selectedItemProperty().removeListener(itemChangeListener);
        }

        this.wrappedModel = wrappedModel;

        if (this.wrappedModel != null) {
            this.wrappedModel.getSelectedItems().addListener(listChangeListener);
            this.wrappedModel.selectedIndexProperty().addListener(indexChangeListener);
            this.wrappedModel.selectedItemProperty().addListener(itemChangeListener);
        }
    }

    public void synchronizeSelection() {
        selectedItems.forEach(this::select);
    }

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return wrappedModel.getSelectedIndices();
    }

    @Override
    public void selectIndices(int index, int... indices) {
        wrappedModel.selectIndices(index, indices);
    }

    @Override
    public void selectAll() {
        wrappedModel.selectAll();
    }

    @Override
    public void selectFirst() {
        wrappedModel.selectFirst();
    }

    @Override
    public void selectLast() {
        wrappedModel.selectLast();
    }

    @Override
    public void clearAndSelect(int index) {
        wrappedModel.clearAndSelect(index);
    }

    @Override
    public void select(int index) {
        wrappedModel.select(index);
    }

    @Override
    public void clearSelection(int index) {
        wrappedModel.clearSelection(index);
    }

    @Override
    public void clearSelection() {
        wrappedModel.clearSelection();
    }

    @Override
    public boolean isSelected(int index) {
        return wrappedModel.isSelected(index);
    }

    @Override
    public boolean isEmpty() {
        return wrappedModel.isEmpty();
    }

    @Override
    public void selectPrevious() {
        wrappedModel.selectPrevious();
    }

    @Override
    public void selectNext() {
        wrappedModel.selectNext();
    }

    @Override
    public ObservableList<Type> getSelectedItems() {
        return this.selectedItems;
    }

    @Override
    public void select(Type obj) {
        wrappedModel.select(convertTo(obj));
    }

    protected abstract Type convertFrom(final WrapperType wrapperType);

    protected abstract WrapperType convertTo(final Type type);
}
