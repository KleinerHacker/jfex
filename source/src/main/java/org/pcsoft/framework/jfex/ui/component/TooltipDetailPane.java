package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;


public class TooltipDetailPane extends VBox {
    private final StringProperty title = new SimpleStringProperty(), description = new SimpleStringProperty();
    // prevents controller for GC clean
    @SuppressWarnings("pmd:UnusedPrivateField")
    private final TooltipDetailPaneView controller;

    public TooltipDetailPane() {
        final ViewTuple<TooltipDetailPaneView, TooltipDetailPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(TooltipDetailPaneView.class).root(this).load();
        //Save instance
        controller = viewTuple.getCodeBehind();

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
