package org.pcsoft.framework.jfex.component;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;


public class HeaderPane extends HBox {

    private final HeaderPaneViewModel viewModel;
    private HeaderPaneView codeBehind;

    public HeaderPane() {
        final ViewTuple<HeaderPaneView, HeaderPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(HeaderPaneView.class).root(this).load();
        codeBehind = viewTuple.getCodeBehind();
        viewModel = viewTuple.getViewModel();
    }

    public HeaderPane(final String title, final String description, final Image image) {
        this();
        setTitle(title);
        setDescription(description);
        setImage(image);
    }

    public HeaderPane(final String title, final String description) {
        this(title, description, null);
    }

    public String getTitle() {
        return viewModel.getTitle();
    }

    public StringProperty titleProperty() {
        return viewModel.titleProperty();
    }

    public void setImage(Image image) {
        viewModel.setImage(image);
    }

    public String getDescription() {
        return viewModel.getDescription();
    }

    public StringProperty descriptionProperty() {
        return viewModel.descriptionProperty();
    }

    public void setTitle(String title) {
        viewModel.setTitle(title);
    }

    public void setDescription(String description) {
        viewModel.setDescription(description);
    }

    public Image getImage() {
        return viewModel.getImage();
    }

    public ObjectProperty<Image> imageProperty() {
        return viewModel.imageProperty();
    }

    public EventHandler<ActionEvent> getOnHelpAction() {
        return viewModel.getOnHelpAction();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onHelpActionProperty() {
        return viewModel.onHelpActionProperty();
    }

    public void setOnHelpAction(EventHandler<ActionEvent> onHelpAction) {
        viewModel.setOnHelpAction(onHelpAction);
    }

    public Image getHelpIcon() {
        return viewModel.getHelpIcon();
    }

    public ObjectProperty<Image> helpIconProperty() {
        return viewModel.helpIconProperty();
    }

    public void setHelpIcon(Image helpIcon) {
        viewModel.setHelpIcon(helpIcon);
    }
}
