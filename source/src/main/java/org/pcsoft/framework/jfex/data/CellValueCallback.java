package org.pcsoft.framework.jfex.data;

import javafx.beans.value.ObservableValue;


@FunctionalInterface
public interface CellValueCallback<S, T> {

    ObservableValue<T> getObservableValue(S value);
}
