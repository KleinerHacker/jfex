package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import java.util.function.Function;

/**
 * Extension variant for a property sheet, based on ControlsFX component
 */
public class PropertySheetEx extends BorderPane {
    private final PropertySheet propertySheet;
    private final PropertySheetCallback propertyEditorFactory;

    public PropertySheetEx() {
        propertySheet = new PropertySheet();
        propertyEditorFactory = new PropertySheetCallback(propertySheet.getPropertyEditorFactory());
        propertySheet.setPropertyEditorFactory(propertyEditorFactory);

        setCenter(propertySheet);
    }

    /**
     * Add support for a custom type and use the given property editor. Can be overwritten with {@link #addItemSupport(PropertySheet.Item, Function)}
     * @param type
     * @param propertyEditorCallback
     * @param <T>
     */
    public <T> void addCustomTypeSupport(Class<T> type, Function<PropertySheet.Item, PropertyEditor> propertyEditorCallback) {
        propertyEditorFactory.getTypeEditorMap().put(type, propertyEditorCallback);
    }

    public <T> boolean supportCustomType(Class<T> type) {
        return propertyEditorFactory.getTypeEditorMap().containsKey(type);
    }

    public <T> void removeCustomTypeSupport(Class<T> type) {
        propertyEditorFactory.getTypeEditorMap().remove(type);
    }

    /**
     * Add support for a concrete item instance and overwrite property editor of {@link #addCustomTypeSupport(Class, Function)}
     * @param item
     * @param propertyEditorCallback
     */
    public void addItemSupport(PropertySheet.Item item, Function<PropertySheet.Item, PropertyEditor> propertyEditorCallback) {
        propertyEditorFactory.getItemEditorMap().put(item, propertyEditorCallback);
    }

    public boolean supportItem(PropertySheet.Item item) {
        return propertyEditorFactory.getItemEditorMap().containsKey(item);
    }

    public void removeItemSupport(PropertySheet.Item item) {
        propertyEditorFactory.getItemEditorMap().remove(item);
    }

    //<editor-fold desc="Delegates">
    public void setTooltip(Tooltip value) {
        propertySheet.setTooltip(value);
    }

    public Tooltip getTooltip() {
        return propertySheet.getTooltip();
    }

    public ObjectProperty<ContextMenu> contextMenuProperty() {
        return propertySheet.contextMenuProperty();
    }

    public void setContextMenu(ContextMenu value) {
        propertySheet.setContextMenu(value);
    }

    public ContextMenu getContextMenu() {
        return propertySheet.getContextMenu();
    }

    public ObservableList<PropertySheet.Item> getItems() {
        return propertySheet.getItems();
    }

    public SimpleObjectProperty<PropertySheet.Mode> modeProperty() {
        return propertySheet.modeProperty();
    }

    public PropertySheet.Mode getMode() {
        return propertySheet.getMode();
    }

    public void setMode(PropertySheet.Mode mode) {
        propertySheet.setMode(mode);
    }

    public SimpleBooleanProperty modeSwitcherVisibleProperty() {
        return propertySheet.modeSwitcherVisibleProperty();
    }

    public boolean isModeSwitcherVisible() {
        return propertySheet.isModeSwitcherVisible();
    }

    public void setModeSwitcherVisible(boolean visible) {
        propertySheet.setModeSwitcherVisible(visible);
    }

    public SimpleBooleanProperty searchBoxVisibleProperty() {
        return propertySheet.searchBoxVisibleProperty();
    }

    public boolean isSearchBoxVisible() {
        return propertySheet.isSearchBoxVisible();
    }

    public void setSearchBoxVisible(boolean visible) {
        propertySheet.setSearchBoxVisible(visible);
    }

    public SimpleStringProperty titleFilter() {
        return propertySheet.titleFilter();
    }

    public String getTitleFilter() {
        return propertySheet.getTitleFilter();
    }

    public void setTitleFilter(String filter) {
        propertySheet.setTitleFilter(filter);
    }

    public ObjectProperty<Tooltip> tooltipProperty() {
        return propertySheet.tooltipProperty();
    }
    //</editor-fold>
}
                                                        