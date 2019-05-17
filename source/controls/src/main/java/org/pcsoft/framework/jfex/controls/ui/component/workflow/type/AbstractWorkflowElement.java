package org.pcsoft.framework.jfex.controls.ui.component.workflow.type;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public abstract class AbstractWorkflowElement<T extends AbstractWorkflowElement> implements WorkflowElement<T> {
    private static final List<Integer> UUID_LIST = new ArrayList<>();

    private static String generateUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (UUID_LIST.contains(uuid.hashCode()));

        UUID_LIST.add(uuid.hashCode());
        return uuid;
    }

    private final ObservableMap<String, WorkflowProperty> properties = FXCollections.observableHashMap();
    private final DoubleProperty x = new SimpleDoubleProperty(), y = new SimpleDoubleProperty();
    private final ReadOnlyStringProperty uuid;

    protected final ObservableMap<String, WorkflowProperty> getElementPropertyMap() {
        return properties;
    }

    public AbstractWorkflowElement() {
        this(-1, -1);
    }

    public AbstractWorkflowElement(double x, double y) {
        this.x.set(x);
        this.y.set(y);
        uuid = new ReadOnlyStringWrapper(generateUUID()).getReadOnlyProperty();
    }

    public AbstractWorkflowElement(T element) {
        this.setX(element.getX());
        this.setY(element.getY());
        this.uuid = new ReadOnlyStringWrapper(element.getUuid()).getReadOnlyProperty();

        this.properties.putAll(
                ((List<WorkflowProperty>) element.getElementPropertyList()).stream()
                        .map(WorkflowProperty::clone)
                        .collect(Collectors.toMap(WorkflowProperty::getName, item -> item))
        );
    }

    @Override
    public final Collection<WorkflowProperty> getElementPropertyList() {
        return properties.values();
    }

    @Override
    public WorkflowProperty getElementProperty(String name) {
        return properties.get(name);
    }

    @Override
    public double getX() {
        return x.get();
    }

    @Override
    public DoubleProperty xProperty() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x.set(x);
    }

    @Override
    public double getY() {
        return y.get();
    }

    @Override
    public DoubleProperty yProperty() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y.set(y);
    }

    @Override
    public ReadOnlyStringProperty uuidProperty() {
        return uuid;
    }

    @Override
    public String getUuid() {
        return uuid.get();
    }
}
