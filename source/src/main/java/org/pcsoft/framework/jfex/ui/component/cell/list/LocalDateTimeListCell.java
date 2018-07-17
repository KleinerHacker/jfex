package org.pcsoft.framework.jfex.ui.component.cell.list;


import javafx.beans.property.ObjectProperty;
import org.pcsoft.framework.jfex.ui.component.cell.LocalDateTimeCellPane;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;


public class LocalDateTimeListCell extends ListCellBase<LocalDateTime, LocalDateTimeCellPane> {

    public LocalDateTimeListCell() {
        super(LocalDateTimeCellPane.class);
    }

    public LocalDateTimeListCell(FormatStyle dateFormatStyle, FormatStyle timeFormatStyle) {
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
