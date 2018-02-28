package org.pcsoft.framework.jfex.component;

import javafx.util.Callback;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class PropertySheetCallback implements Callback<PropertySheet.Item, PropertyEditor<?>> {
    private final Map<PropertySheet.Item, Function<PropertySheet.Item, PropertyEditor>> itemEditorMap = new HashMap<>();
    private final Map<Class<?>, Function<PropertySheet.Item, PropertyEditor>> typeEditorMap = new HashMap<>();
    private final Callback<PropertySheet.Item, PropertyEditor<?>> originalCallback;

    public PropertySheetCallback(Callback<PropertySheet.Item, PropertyEditor<?>> originalCallback) {
        this.originalCallback = originalCallback;
    }

    public Map<PropertySheet.Item, Function<PropertySheet.Item, PropertyEditor>> getItemEditorMap() {
        return itemEditorMap;
    }

    public Map<Class<?>, Function<PropertySheet.Item, PropertyEditor>> getTypeEditorMap() {
        return typeEditorMap;
    }

    public Callback<PropertySheet.Item, PropertyEditor<?>> getOriginalCallback() {
        return originalCallback;
    }

    @Override
    public PropertyEditor call(PropertySheet.Item param) {
        if (!itemEditorMap.containsKey(param)) {
            final PropertyEditor<?> propertyEditor = originalCallback.call(param);
            if (propertyEditor != null)
                return propertyEditor;

            return typeEditorMap.get(param.getType()).apply(param);
        }

        return itemEditorMap.get(param).apply(param);
    }
}
