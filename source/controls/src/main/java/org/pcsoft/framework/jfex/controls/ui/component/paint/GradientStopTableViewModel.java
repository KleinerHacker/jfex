package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Stop;
import org.pcsoft.framework.jfex.mvvm.FxmlViewModel;

public class GradientStopTableViewModel implements FxmlViewModel {
    private final ReadOnlyListProperty<Stop> stopList =
            new ReadOnlyListWrapper<Stop>(FXCollections.observableArrayList()).getReadOnlyProperty();

    public ObservableList<Stop> getStopList() {
        return stopList.get();
    }

    public ReadOnlyListProperty<Stop> stopListProperty() {
        return stopList;
    }
}
