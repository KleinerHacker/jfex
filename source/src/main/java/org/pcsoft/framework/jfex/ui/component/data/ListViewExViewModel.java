package org.pcsoft.framework.jfex.ui.component.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;


public class ListViewExViewModel<T, G> extends MultipleSelectionDataViewModel<T, G, ListCell> {
    private final StringProperty emptyText = new SimpleStringProperty("No Data");
    private final StringProperty emptyTextStyle = new SimpleStringProperty();
    private final ObjectProperty<EventHandler<ListItemClickEvent<T>>> onItemClick = new SimpleObjectProperty<>();
    private final BooleanProperty showToolbar = new SimpleBooleanProperty(false);
    private final ReadOnlyListProperty<Node> toolbarItems = new ReadOnlyListWrapper<Node>(FXCollections.observableArrayList()).getReadOnlyProperty();
    private final ObjectProperty<ListViewEx.ToolbarOrientation> orientation = new SimpleObjectProperty<>(ListViewEx.ToolbarOrientation.TOP);

    public ListViewEx.ToolbarOrientation getOrientation() {
        return orientation.get();
    }

    public ObjectProperty<ListViewEx.ToolbarOrientation> orientationProperty() {
        return orientation;
    }

    public void setOrientation(ListViewEx.ToolbarOrientation orientation) {
        this.orientation.set(orientation);
    }

    public ObservableList<Node> getToolbarItems() {
        return toolbarItems.get();
    }

    public ReadOnlyListProperty<Node> toolbarItemsProperty() {
        return toolbarItems;
    }

    public boolean isShowToolbar() {
        return showToolbar.get();
    }

    public BooleanProperty showToolbarProperty() {
        return showToolbar;
    }

    public void setShowToolbar(boolean showToolbar) {
        this.showToolbar.set(showToolbar);
    }

    public String getEmptyTextStyle() {
        return emptyTextStyle.get();
    }

    public StringProperty emptyTextStyleProperty() {
        return emptyTextStyle;
    }

    public void setEmptyTextStyle(String emptyTextStyle) {
        this.emptyTextStyle.set(emptyTextStyle);
    }

    public String getEmptyText() {
        return emptyText.get();
    }

    public StringProperty emptyTextProperty() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText.set(emptyText);
    }

    public EventHandler<ListItemClickEvent<T>> getOnItemClick() {
        return onItemClick.get();
    }

    public ObjectProperty<EventHandler<ListItemClickEvent<T>>> onItemClickProperty() {
        return onItemClick;
    }

    public void setOnItemClick(EventHandler<ListItemClickEvent<T>> onItemClick) {
        this.onItemClick.set(onItemClick);
    }
}
