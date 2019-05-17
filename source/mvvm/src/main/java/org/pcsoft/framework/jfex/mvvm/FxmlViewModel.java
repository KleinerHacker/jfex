package org.pcsoft.framework.jfex.mvvm;

public interface FxmlViewModel {
    default void onAttached() {}
    default void onDetached() {}
}
