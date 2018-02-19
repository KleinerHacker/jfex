package org.pcsoft.framework.jfex.component.cell;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.HBox;

import java.time.LocalDate;


public class LocalDateCellPane extends HBox implements CellPane<LocalDate> {

    private final ObjectProperty<LocalDate> value = new SimpleObjectProperty<>();
    private final LocalDateCellPaneView controller;

    public LocalDateCellPane() {
        final ViewTuple<LocalDateCellPaneView, LocalDateCellPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(LocalDateCellPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();

        this.value.addListener((v, o, n) -> viewTuple.getViewModel().updateDate(n));
    }

    public LocalDateCellPane(LocalDate value) {
        this();
        this.value.set(value);
    }

    @Override
    public LocalDate getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty<LocalDate> valueProperty() {
        return value;
    }

    @Override
    public void setValue(LocalDate value) {
        this.value.set(value);
    }
}
