package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.format.FormatStyle;


public class LocalDateCellPane extends HBox implements CellPane<LocalDate> {

    private final LocalDateCellPaneView controller;
    private final LocalDateCellPaneViewModel viewModel;

    public LocalDateCellPane() {
        final ViewTuple<LocalDateCellPaneView, LocalDateCellPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(LocalDateCellPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
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
