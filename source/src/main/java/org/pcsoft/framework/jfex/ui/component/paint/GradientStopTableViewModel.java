package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Stop;

public class GradientStopTableViewModel implements ViewModel {
    private final ReadOnlyListProperty<Stop> stopList =
            new ReadOnlyListWrapper<Stop>(FXCollections.observableArrayList()).getReadOnlyProperty();

    public ObservableList<Stop> getStopList() {
        return stopList.get();
    }

    public ReadOnlyListProperty<Stop> stopListProperty() {
        return stopList;
    }
}
