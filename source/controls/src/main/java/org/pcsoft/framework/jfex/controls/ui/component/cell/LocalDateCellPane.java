package org.pcsoft.framework.jfex.controls.ui.component.cell;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.HBox;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.time.LocalDate;
import java.time.format.FormatStyle;


public class LocalDateCellPane extends HBox implements CellPane<LocalDate> {
    private final LocalDateCellPaneView controller;
    private final LocalDateCellPaneViewModel viewModel;

    public LocalDateCellPane() {
        final Fxml.FxmlTuple<LocalDateCellPaneView, LocalDateCellPaneViewModel> viewTuple =
                Fxml.from(LocalDateCellPaneView.class).withRoot(this).load();
        controller = viewTuple.getViewController();
        viewModel = viewTuple.getViewModel();
    }

    public LocalDateCellPane(LocalDate value) {
        this();
        setValue(value);
    }

    public LocalDateCellPane(LocalDate value, FormatStyle formatter) {
        this(value);
        setDateFormatStyle(formatter);
    }

    @Override
    public LocalDate getValue() {
        return viewModel.getDate();
    }

    @Override
    public ObjectProperty<LocalDate> valueProperty() {
        return viewModel.dateProperty();
    }

    @Override
    public void setValue(LocalDate value) {
        viewModel.setDate(value);
    }

    public FormatStyle getDateFormatStyle() {
        return viewModel.getDateFormatStyle();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return viewModel.dateFormatStyleProperty();
    }

    public void setDateFormatStyle(FormatStyle dateTimeFormatter) {
        viewModel.setDateFormatStyle(dateTimeFormatter);
    }
}
