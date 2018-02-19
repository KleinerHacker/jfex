package org.pcsoft.framework.jfex.wizard;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;
import org.pcsoft.framework.jfex.component.HeaderPane;

/**
 * Represent the base for a wizard page
 */
public abstract class FXWizardPage<T extends FXWizardModel> extends WizardPane {
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<Image> icon = new SimpleObjectProperty<>();
    private final BooleanProperty invalid = new SimpleBooleanProperty(), canGoBack = new SimpleBooleanProperty(true), canCancel = new SimpleBooleanProperty(true);
    private final ReadOnlyObjectWrapper<Wizard> wizardWrapper = new ReadOnlyObjectWrapper<>();
    protected final ReadOnlyObjectProperty<Wizard> wizard = wizardWrapper.getReadOnlyProperty();

    private InvalidationListener goBackInvalidationListener = null, cancelInvalidationListener = null;

    public FXWizardPage() {
        final HeaderPane headerPane = new HeaderPane();
        headerPane.titleProperty().bind(Bindings.createStringBinding(() -> {
            if (getScene() == null || getScene().getWindow() == null)
                return "";
            return ((Stage) getScene().getWindow()).getTitle();
        }, sceneProperty()));
        headerPane.descriptionProperty().bind(description);
        headerPane.imageProperty().bind(icon);

        setHeader(headerPane);

        if (this instanceof FXWizardFinishPage) {
            canGoBack.set(false);
            canCancel.set(false);
        }

        canGoBack.addListener(o -> {
            if (goBackInvalidationListener != null) {
                goBackInvalidationListener.invalidated(canGoBack);
            }
        });
        canCancel.addListener(o -> {
            if (cancelInvalidationListener != null) {
                cancelInvalidationListener.invalidated(canCancel);
            }
        });
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

    public boolean getInvalid() {
        return invalid.get();
    }

    public BooleanProperty invalidProperty() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid.set(invalid);
    }

    public boolean isCanGoBack() {
        return canGoBack.get();
    }

    public BooleanProperty canGoBackProperty() {
        return canGoBack;
    }

    public void setCanGoBack(boolean canGoBack) {
        this.canGoBack.set(canGoBack);
    }

    public boolean isCanCancel() {
        return canCancel.get();
    }

    public BooleanProperty canCancelProperty() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel.set(canCancel);
    }

    @Override
    public void onEnteringPage(Wizard wizard) {
        wizard.invalidProperty().bind(invalid);
        updateWizardModel(((FXWizard<T>) wizard).getWizardModel());
        wizardWrapper.set(wizard);

        for (final ButtonType buttonType : getButtonTypes()) {
            if (buttonType.getButtonData() == ButtonBar.ButtonData.BACK_PREVIOUS) {
                final Node button = lookupButton(buttonType);

                button.setDisable(!canGoBack.get());
                goBackInvalidationListener = o -> button.setDisable(button.isDisabled() || !canGoBack.get());
                button.disableProperty().addListener(goBackInvalidationListener);
            } else if (buttonType.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                final Node button = lookupButton(buttonType);

                button.setDisable(!canCancel.get());
                cancelInvalidationListener = o -> button.setDisable(button.isDisabled() || !canCancel.get());
                button.disableProperty().addListener(cancelInvalidationListener);
            }
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {
        wizard.invalidProperty().unbind();
        wizardWrapper.set(null);

        for (final ButtonType buttonType : getButtonTypes()) {
            if (buttonType.getButtonData() == ButtonBar.ButtonData.BACK_PREVIOUS) {
                final Node button = lookupButton(buttonType);

                button.disableProperty().removeListener(goBackInvalidationListener);
                goBackInvalidationListener = null;
            } else if (buttonType.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                final Node button = lookupButton(buttonType);

                button.disableProperty().removeListener(cancelInvalidationListener);
                cancelInvalidationListener = null;
            }
        }
    }

    protected abstract void updateWizardModel(final T model);
}
