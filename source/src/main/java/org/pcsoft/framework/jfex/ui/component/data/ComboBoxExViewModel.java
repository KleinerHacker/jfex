package org.pcsoft.framework.jfex.ui.component.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;
import org.pcsoft.framework.jfex.property.SimpleWrapperProperty;


public class ComboBoxExViewModel<T, G> extends SimpleDataViewModel<T, G, ListCell> {
    private final ObjectProperty<T> value = new SimpleObjectProperty<>();
    private final ObjectProperty<CellRendererCallback<ListCell, T>> valueButtonCellRendererCallback = new SimpleObjectProperty<>();
    private final StringProperty promptText = new SimpleStringProperty();

    //region Internal
    private final ObjectProperty<Item> selectedItem = new SimpleWrapperProperty<Item, T>(value) {
        @Override
        protected Item convertTo(T value) {
            return new ItemValue<>(value);
        }

        @Override
        protected T convertFrom(Item value) {
            return (T) value.getData();
        }
    };
    //endregion

    //region Internal
    public Item getSelectedItem() {
        return selectedItem.get();
    }

    public ObjectProperty<Item> selectedItemProperty() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem.set(selectedItem);
    }
    //endregion


    public String getPromptText() {
        return promptText.get();
    }

    public StringProperty promptTextProperty() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText.set(promptText);
    }

    public T getValue() {
        return value.get();
    }

    public ObjectProperty<T> valueProperty() {
        return value;
    }

    public void setValue(T value) {
        this.value.set(value);
    }

    public CellRendererCallback<ListCell, T> getValueButtonCellRendererCallback() {
        return valueButtonCellRendererCallback.get();
    }

    public ObjectProperty<CellRendererCallback<ListCell, T>> valueButtonCellRendererCallbackProperty() {
        return valueButtonCellRendererCallback;
    }

    public void setValueButtonCellRendererCallback(CellRendererCallback<ListCell, T> valueButtonCellRendererCallback) {
        this.valueButtonCellRendererCallback.set(valueButtonCellRendererCallback);
    }
}
