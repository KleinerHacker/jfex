package org.pcsoft.framework.jfex.workflow.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.workflow.type.BasicWorkflowElement;
import org.pcsoft.framework.jfex.workflow.type.WorkflowElementInfo;
import org.pcsoft.framework.jfex.workflow.type.WorkflowProperty;


@WorkflowElementInfo(name = "Process", description = "Simple Process Element", group = "Basics",
        smallIconUrl = "/icons/ic_process16.png", bigIconUrl = "/icons/ic_process48.png")
public class WorkflowProcessElement extends BasicWorkflowElement<WorkflowProcessElement> {
    public static final String PROP_GROUP_DESIGN = "Design";
    public static final String PROP_GROUP_SPECIAL = "Special";

    public static final String PROP_NAME = "Name";
    public static final String PROP_COLOR = "Color";

    private final StringProperty name = new SimpleStringProperty("<UNKNOWN>");
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.YELLOW);

    public WorkflowProcessElement(double x, double y) {
        super(x, y);
        getElementPropertyMap().put(PROP_NAME, new WorkflowProperty<>(PROP_NAME, PROP_GROUP_SPECIAL, name));
        getElementPropertyMap().put(PROP_COLOR,new WorkflowProperty<>(PROP_COLOR, PROP_GROUP_DESIGN, color));
    }

    public WorkflowProcessElement() {
    }

    private WorkflowProcessElement(WorkflowProcessElement element) {
        super(element);
        this.name.set(element.getName());
        this.color.set(element.getColor());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    @Override
    public WorkflowProcessElement copy() {
        return new WorkflowProcessElement(this);
    }
}
