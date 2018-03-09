package org.pcsoft.framework.jfex.ui.wizard;

import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;


public abstract class FXWizardFlow<T extends FXWizardModel> implements Wizard.Flow {
    private T model;

    protected final T getModel() {
        return model;
    }

    final void setModel(T model) {
        this.model = model;
    }

    @Override
    public boolean canAdvance(WizardPane currentPage) {
        if (currentPage instanceof FXWizardFinishPage)
            return false;

        return true;
    }
}
