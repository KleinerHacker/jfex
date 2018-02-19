package org.pcsoft.framework.jfex.workflow.editor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.pcsoft.framework.jfex.util.EventHandlerUtils;
import org.pcsoft.framework.jfex.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.workflow.type.WorkflowPropertyEditorInfo;


@WorkflowPropertyEditorInfo(propertyType = ObjectProperty.class, innerPropertyType = Character.class)
public final class WorkflowCharacterPropertyEditor extends TextField implements WorkflowPropertyEditor<Character, ObjectProperty<Character>> {
    private final ObjectProperty<Character> property;

    public WorkflowCharacterPropertyEditor() {
        addEventHandler(KeyEvent.KEY_TYPED, EventHandlerUtils.TextFieldHandlers.createLengthInputRestrictionHandler(1));
        property = new SimpleObjectProperty<Character>() {
            {
                textProperty().addListener(o -> fireValueChangedEvent());
            }

            @Override
            public Character get() {
                return getValue();
            }

            @Override
            public void set(Character newValue) {
                setValue(newValue);
            }

            @Override
            public void setValue(Character v) {
                textProperty().setValue(v == null ? null : ("" + v));
            }

            @Override
            public Character getValue() {
                return getText() == null || getText().isEmpty() ? null : getText().charAt(0);
            }
        };
    }

    @Override
    public Character getValue() {
        return getText().charAt(0);
    }

    @Override
    public void setValue(Character value) {
        setText(value + "");
    }

    @Override
    public ObjectProperty<Character> valueProperty() {
        return property;
    }
}
