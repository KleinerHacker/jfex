package org.pcsoft.framework.jfex.type;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import org.pcsoft.framework.jfex.ui.component.data.CellRendererCallback;
import org.pcsoft.framework.jfex.ui.component.data.CellValueCallback;

import java.util.List;


public abstract class TableColumnWrapper<Type, WrappedType, T> extends TableColumnBase<Type, T> {
    private TableColumn<WrappedType, T> wrappedColumn;

    private final ObjectProperty<CellRendererCallback<TableCell, T>> cellRendererCallback = new SimpleObjectProperty<>((cell, item, empty) -> {
    });
    private final ObjectProperty<CellValueCallback<Type, T>> cellValueFactory =
            new SimpleObjectProperty<>(value -> new ReadOnlyObjectWrapper<T>(null).getReadOnlyProperty());
    private final ObservableList<TableColumnWrapper<Type, WrappedType, T>> columns = FXCollections.observableArrayList();

    public TableColumnWrapper() {
        cellRendererCallback.addListener(o -> {
            wrappedColumn.setCellFactory(param -> new TableCell<WrappedType, T>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(null);
                    setGraphic(null);

                    if (cellRendererCallback.get() != null) {
                        cellRendererCallback.get().onRender(this, item, empty);
                    }
                }
            });
        });
        cellValueFactory.addListener((v, o, n) -> {
            wrappedColumn.setCellValueFactory(param -> cellValueFactory.get() == null ? null : cellValueFactory.get().getObservableValue(extractFromWrappedType(param.getValue())));
        });
    }

    public TableColumnWrapper(TableColumn<WrappedType, T> wrappedColumn) {
        this.wrappedColumn = wrappedColumn;
        this.setText(wrappedColumn.getText());
    }

    public TableColumn<WrappedType, T> getWrappedColumn() {
        return wrappedColumn;
    }

    public void setWrappedColumn(TableColumn<WrappedType, T> wrappedColumn) {
        this.wrappedColumn = wrappedColumn;
        this.setText(wrappedColumn.getText());
    }

    @Override
    public ObservableList<TableColumnWrapper<Type, WrappedType, T>> getColumns() {
        return columns;
    }

    @Override
    public ObservableValue<T> getCellObservableValue(int index) {
        return getCellObservableValue(extractFromWrappedType(wrappedColumn.getTableView().getItems().get(index)));
    }

    @Override
    public ObservableValue<T> getCellObservableValue(Type item) {
        return wrappedColumn.getCellValueFactory().call(new TableColumn.CellDataFeatures<>(wrappedColumn.getTableView(), wrappedColumn, convertIntoWrappedType(item)));
    }

    @Override
    public String getTypeSelector() {
        return wrappedColumn.getTypeSelector();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return wrappedColumn.getCssMetaData();
    }

    @Override
    public Styleable getStyleableParent() {
        return wrappedColumn.getStyleableParent();
    }

    protected abstract WrappedType convertIntoWrappedType(final Type type);
    protected abstract Type extractFromWrappedType(final WrappedType wrappedType);
}
