package org.pcsoft.framework.jfex.ui.component.cell;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;


public class LocalDateTimeCellPane extends HBox implements CellPane<LocalDateTime> {

    private final LocalDateTimeCellPaneView controller;
    private final LocalDateTimeCellPaneViewModel viewModel;

    public LocalDateTimeCellPane() {
        final ViewTuple<LocalDateTimeCellPaneView, LocalDateTimeCellPaneViewModel> viewTuple =
                FluentViewLoader.fxmlView(LocalDateTimeCellPaneView.class).root(this).load();
        controller = viewTuple.getCodeBehind();
        viewModel = viewTuple.getViewModel();
    }

    public LocalDateTimeCellPane(LocalDateTime value) {
        this();
        setValue(value);
    }

    public LocalDateTimeCellPane(LocalDateTime value, FormatStyle dateFormatStyle, FormatStyle timeFormatStyle) {
        this(value);
        setDateFormatStyle(dateFormatStyle);
        setTimeFormatStyle(timeFormatStyle);
    }

    @Override
    public LocalDateTime getValue() {
        return viewModel.getDateTime();
    }

    @Override
    public ObjectProperty<LocalDateTime> valueProperty() {
        return viewModel.dateTimeProperty();
    }

    @Override
    public void setValue(LocalDateTime value) {
        viewModel.setDateTime(value);
    }

    public FormatStyle getDateFormatStyle() {
        return viewModel.getDateFormatStyle();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return viewModel.dateFormatStyleProperty();
    }

    public void setDateFormatStyle(FormatStyle dateFormatStyle) {
        viewModel.setDateFormatStyle(dateFormatStyle);
    }

    public FormatStyle getTimeFormatStyle() {
        return viewModel.getTimeFormatStyle();
    }

    public ObjectProperty<FormatStyle> timeFormatStyleProperty() {
        return viewModel.timeFormatStyleProperty();
    }

    public void setTimeFormatStyle(FormatStyle timeFormatStyle) {
        viewModel.setTimeFormatStyle(timeFormatStyle);
    }
}
