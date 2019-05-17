package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;


public class TooltipDetailPaneViewModel implements FxmlViewModel {
    private final StringProperty title = new SimpleStringProperty(), description = new SimpleStringProperty();

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
}
