package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;


public class WorkflowProcessElementComponentViewModel implements FxmlViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Color> background = new SimpleObjectProperty<>();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Color getBackground() {
        return background.get();
    }

    public ObjectProperty<Color> backgroundProperty() {
        return background;
    }

    public void setBackground(Color background) {
        this.background.set(background);
    }

    void updateProperties(final WorkflowProcessElement processElement) {
        name.bind(processElement.nameProperty());
        background.bind(processElement.colorProperty());
    }
}
