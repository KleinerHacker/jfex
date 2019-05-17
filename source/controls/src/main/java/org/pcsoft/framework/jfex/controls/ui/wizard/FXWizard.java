package org.pcsoft.framework.jfex.controls.ui.wizard;

import org.controlsfx.dialog.Wizard;

/**
 * Extended FX Wizard, based on ControlsFX wizard
 */
public abstract class FXWizard<T extends FXWizardModel> extends Wizard {
    protected final T wizardModel;

    public FXWizard(final Class<T> wizardModelClass, final String title, final FXWizardFlow<T> flow) {
        try {
            wizardModel = wizardModelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        flow.setModel(wizardModel);

        setTitle(title);
        setFlow(flow);
    }

    public T getWizardModel() {
        return wizardModel;
    }
}
