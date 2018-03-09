package org.pcsoft.framework.jfex.ui.component.workflow.editor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorInfo;
import org.pcsoft.framework.jfex.util.EventHandlerUtils;


@WorkflowPropertyEditorInfo(propertyType = IntegerProperty.class)
public final class WorkflowIntegerPropertyEditor extends TextField implements WorkflowPropertyEditor<Number, IntegerProperty> {
    private final IntegerProperty property;

    public WorkflowIntegerPropertyEditor() {
        addEventHandler(KeyEvent.KEY_TYPED, EventHandlerUtils.TextFieldHandlers.createNumericIntegerInputRestrictionHandler());
        property = new SimpleIntegerProperty() {
            {
                textProperty().addListener(o -> fireValueChangedEvent());
            }

            @Override
            public int get() {
                return getValue();
            }

            @Override
            public void set(int newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Number v) {
                setText(String.valueOf(v));
            }

            @Override
            public Integer getValue() {
                return Integer.valueOf(getText());
            }
        };
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(getText());
    }

    @Override
    public void setValue(Number value) {
        setText(String.valueOf(value));
    }

    @Override
    public IntegerProperty valueProperty() {
        return property;
    }
}
