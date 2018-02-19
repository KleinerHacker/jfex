package org.pcsoft.framework.jfex.workflow.component.cell;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import org.pcsoft.framework.jfex.workflow.type.WorkflowElementInfoDescriptor;

import java.io.ByteArrayInputStream;


public class WorkflowElementCellPaneViewModel implements ViewModel {
    private final StringProperty title = new SimpleStringProperty(), description = new SimpleStringProperty();
    private final ObjectProperty<Image> icon = new SimpleObjectProperty<>();

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Image getIcon() {
        return icon.get();
    }

    public ObjectProperty<Image> iconProperty() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon.set(icon);
    }

    void updateProperties(final WorkflowElementInfoDescriptor elementInfo) {
        setTitle(elementInfo.getName());
        setDescription(elementInfo.getDescription());
        setIcon(elementInfo.getSmallIcon() == null ? null : new Image(new ByteArrayInputStream(elementInfo.getSmallIcon())));
    }
}
