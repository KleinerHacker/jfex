package org.pcsoft.framework.jfex.mvvm;

import javafx.fxml.Initializable;

public abstract class FxmlView<T extends FxmlViewModel> implements Initializable {
    protected T viewModel;
}
