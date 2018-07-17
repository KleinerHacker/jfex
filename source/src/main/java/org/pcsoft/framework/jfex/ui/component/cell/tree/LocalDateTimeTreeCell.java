package org.pcsoft.framework.jfex.ui.component.cell.tree;


import javafx.beans.property.ObjectProperty;
import org.pcsoft.framework.jfex.ui.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;


public class LocalDateTimeTreeCell extends TreeCellBase<LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeTreeCell() {
        super(LocalDateTimeCellPane.class);
    }

    public LocalDateTimeTreeCell(FormatStyle dateFormatStyle, FormatStyle timeFormatStyle) {
        this();
        setDateFormatStyle(dateFormatStyle);
        setTimeFormatStyle(timeFormatStyle);
    }

    public FormatStyle getDateFormatStyle() {
        return cellPane.getDateFormatStyle();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return cellPane.dateFormatStyleProperty();
    }

    public void setDateFormatStyle(FormatStyle dateFormatStyle) {
        cellPane.setDateFormatStyle(dateFormatStyle);
    }

    public FormatStyle getTimeFormatStyle() {
        return cellPane.getTimeFormatStyle();
    }

    public ObjectProperty<FormatStyle> timeFormatStyleProperty() {
        return cellPane.timeFormatStyleProperty();
    }

    public void setTimeFormatStyle(FormatStyle timeFormatStyle) {
        cellPane.setTimeFormatStyle(timeFormatStyle);
    }
}
