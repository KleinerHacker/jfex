package org.pcsoft.framework.jfex.ui.component.workflow.editor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditor;
import org.pcsoft.framework.jfex.ui.component.workflow.type.WorkflowPropertyEditorInfo;

import java.io.File;
import java.io.IOException;


@WorkflowPropertyEditorInfo(propertyType = ObjectProperty.class, innerPropertyType = byte[].class)
public final class WorkflowByteArrayPropertyEditor extends Button implements WorkflowPropertyEditor<byte[], ObjectProperty<byte[]>> {
    private final ObjectProperty<byte[]> value = new SimpleObjectProperty<>();

    public WorkflowByteArrayPropertyEditor() {
        setText("...");
        setPrefWidth(50);
        setOnAction(e -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
            final File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    value.setValue(FileUtils.readFileToByteArray(file));
                } catch (IOException e1) {
                    e1.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Unable to open file!", ButtonType.OK).showAndWait();
                }
            }
        });
    }

    @Override
    public byte[] getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty<byte[]> valueProperty() {
        return value;
    }

    @Override
    public void setValue(byte[] value) {
        this.value.set(value);
    }
}
