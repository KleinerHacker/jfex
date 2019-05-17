package org.pcsoft.framework.jfex.controls.ui.component.workflow.type;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;


public final class WorkflowProperty<T extends Property> {
    private final BooleanProperty editorVisible = new SimpleBooleanProperty(true);
    private final BooleanProperty editorEditable = new SimpleBooleanProperty(true);

    private final ReadOnlyStringProperty name;
    private final ReadOnlyStringProperty group;
    private final ReadOnlyObjectProperty<Image> icon;
    private final ReadOnlyObjectProperty<T> property;

    public WorkflowProperty(String name, T property) {
        this(name, (Image) null, property);
    }

    public WorkflowProperty(String name, String group, T property) {
        this(name, group, null, property);
    }

    public WorkflowProperty(String name, Image icon, T property) {
        this(name, null, icon, property);
    }

    public WorkflowProperty(String name, String group, Image icon, T property) {
        this.name = new ReadOnlyStringWrapper(name).getReadOnlyProperty();
        this.group = new ReadOnlyStringWrapper(group).getReadOnlyProperty();
        this.icon = new ReadOnlyObjectWrapper<>(icon).getReadOnlyProperty();
        this.property = new ReadOnlyObjectWrapper<>(property).getReadOnlyProperty();
    }

    private WorkflowProperty(WorkflowProperty<T> workflowProperty) {
        this(workflowProperty.getName(), workflowProperty.getGroup(), workflowProperty.getIcon(), workflowProperty.getProperty());
    }

    public boolean getEditorVisible() {
        return editorVisible.get();
    }

    public BooleanProperty editorVisibleProperty() {
        return editorVisible;
    }

    public void setEditorVisible(boolean editorVisible) {
        this.editorVisible.set(editorVisible);
    }

    public boolean getEditorEditable() {
        return editorEditable.get();
    }

    public BooleanProperty editorEditableProperty() {
        return editorEditable;
    }

    public void setEditorEditable(boolean editorEditable) {
        this.editorEditable.set(editorEditable);
    }

    public String getName() {
        return name.get();
    }

    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    public T getProperty() {
        return property.get();
    }

    public ReadOnlyObjectProperty<T> propertyProperty() {
        return property;
    }

    public String getGroup() {
        return group.get();
    }

    public ReadOnlyStringProperty groupProperty() {
        return group;
    }

    public Image getIcon() {
        return icon.get();
    }

    public ReadOnlyObjectProperty<Image> iconProperty() {
        return icon;
    }

    public WorkflowProperty<T> clone() {
        return new WorkflowProperty<>(this);
    }
}
