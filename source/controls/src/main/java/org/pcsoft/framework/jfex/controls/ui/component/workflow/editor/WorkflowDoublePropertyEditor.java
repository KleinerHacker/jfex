package org.pcsoft.framework.jfex.controls.ui.component.workflow.editor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.pcsoft.framework.jfex.controls.util.EventHandlerUtils;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowPropertyEditorInfo;


@WorkflowPropertyEditorInfo(propertyType = DoubleProperty.class)
public final class WorkflowDoublePropertyEditor extends TextField implements WorkflowPropertyEditor<Number, DoubleProperty> {
    private final DoubleProperty property;

    public WorkflowDoublePropertyEditor() {
        addEventHandler(KeyEvent.KEY_TYPED, EventHandlerUtils.TextFieldHandlers.createNumericDecimalInputRestrictionHandler());
        property = new SimpleDoubleProperty() {
            {
                textProperty().addListener(o -> fireValueChangedEvent());
            }

            @Override
            public double get() {
                return getValue();
            }

            @Override
            public void set(double newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Number v) {
                setText(String.valueOf(v));
            }

            @Override
            public Double getValue() {
                return Double.valueOf(getText());
            }
        };
    }

    @Override
    public Number getValue() {
        return Double.valueOf(getText());
    }

    @Override
    public void setValue(Number value) {
        setText(String.valueOf(value));
    }

    @Override
    public DoubleProperty valueProperty() {
        return property;
    }
}
