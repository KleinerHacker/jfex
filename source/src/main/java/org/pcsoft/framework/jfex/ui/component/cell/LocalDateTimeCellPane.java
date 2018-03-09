package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;


public class LocalDateTimeCellPane extends HBox implements CellPane<LocalDateTime> {

    private final ObjectProperty<LocalDateTime> value = new SimpleObjectProperty<>();
    private final LocalDateTimeCellPaneView controller;

    public LocalDateTimeCellPane() {
        final ViewTuple<LocalDateTimeCellPaneView, LocalDateTimeCellPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(LocalDateTimeCellPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();

        this.value.addListener((v, o, n) -> viewTuple.getViewModel().updateDateTime(n));
    }

    public LocalDateTimeCellPane(LocalDateTime value) {
        this();
        this.value.set(value);
    }

    @Override
    public LocalDateTime getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty<LocalDateTime> valueProperty() {
        return value;
    }

    @Override
    public void setValue(LocalDateTime value) {
        this.value.set(value);
    }
}
