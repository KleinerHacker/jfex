package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import org.pcsoft.framework.jfex.mvvm.Fxml;


public class TooltipDetailPane extends VBox {
    private final StringProperty title = new SimpleStringProperty(), description = new SimpleStringProperty();
    // prevents controller for GC clean
    @SuppressWarnings("pmd:UnusedPrivateField")
    private final TooltipDetailPaneView controller;

    public TooltipDetailPane() {
        final Fxml.FxmlTuple<TooltipDetailPaneView, TooltipDetailPaneViewModel> viewTuple =
                Fxml.from(TooltipDetailPaneView.class).withRoot(this).load();
        //Save instance
        controller = viewTuple.getViewController();

        viewTuple.getViewModel().titleProperty().bind(title);
        viewTuple.getViewModel().descriptionProperty().bind(description);
    }

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
