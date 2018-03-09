package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;


public class HeaderPaneViewModel implements ViewModel {
    private final StringProperty title = new SimpleStringProperty(), description = new SimpleStringProperty();
    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private final ObjectProperty<EventHandler<ActionEvent>> onHelpAction = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> helpIcon = new SimpleObjectProperty<>();

    private final ObjectBinding<Image> helpIconBinding;
    private final BooleanBinding helpIconVisible;

    public HeaderPaneViewModel() {
        helpIconBinding = Bindings.when(helpIcon.isNull())
                .then(new Image(getClass().getResourceAsStream("/icons/ic_help.png")))
                .otherwise(helpIcon);
        helpIconVisible = onHelpAction.isNotNull();
    }

    public Boolean getHelpIconVisible() {
        return helpIconVisible.get();
    }

    public BooleanBinding helpIconVisibleProperty() {
        return helpIconVisible;
    }

    public EventHandler<ActionEvent> getOnHelpAction() {
        return onHelpAction.get();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onHelpActionProperty() {
        return onHelpAction;
    }

    public void setOnHelpAction(EventHandler<ActionEvent> onHelpAction) {
        this.onHelpAction.set(onHelpAction);
    }

    public Image getHelpIcon() {
        return helpIcon.get();
    }

    public ObjectProperty<Image> helpIconProperty() {
        return helpIcon;
    }

    public void setHelpIcon(Image helpIcon) {
        this.helpIcon.set(helpIcon);
    }

    public Image getHelpIconBinding() {
        return helpIconBinding.get();
    }

    public ObjectBinding<Image> helpIconBindingProperty() {
        return helpIconBinding;
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

    public Image getImage() {
        return image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }
}
