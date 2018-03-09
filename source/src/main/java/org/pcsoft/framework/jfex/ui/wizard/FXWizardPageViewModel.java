package org.pcsoft.framework.jfex.ui.wizard;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * View Model for a wizard page
 */
public abstract class FXWizardPageViewModel<T extends FXWizardModel> implements ViewModel {
    private final ObjectProperty<T> model = new SimpleObjectProperty<>();

    public FXWizardPageViewModel() {
        model.addListener((v, o, n) -> {
            if (o != null) {
                unbindWizardModel(o);
            }
            if (n != null) {
                bindWizardModel(n);
            }
        });
    }

    public T getModel() {
        return model.get();
    }

    public ObjectProperty<T> modelProperty() {
        return model;
    }

    public void setModel(T model) {
        this.model.set(model);
    }

    protected abstract void bindWizardModel(final T newModel);
    protected abstract void unbindWizardModel(final T oldModel);
}
