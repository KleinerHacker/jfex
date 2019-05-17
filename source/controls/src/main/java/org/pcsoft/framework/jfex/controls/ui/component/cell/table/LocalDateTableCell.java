package org.pcsoft.framework.jfex.controls.ui.component.cell.table;


import javafx.beans.property.ObjectProperty;
import org.pcsoft.framework.jfex.controls.ui.component.cell.LocalDateCellPane;

import java.time.LocalDate;
import java.time.format.FormatStyle;


public class LocalDateTableCell<T> extends TableCellBase<T, LocalDate, LocalDateCellPane> {
    public LocalDateTableCell() {
        super(LocalDateCellPane.class);
    }

    public LocalDateTableCell(FormatStyle dateFormatStyle) {
        this();
        setDateFormatStyle(dateFormatStyle);
    }

    public FormatStyle getDateFormatStyle() {
        return cellPane.getDateFormatStyle();
    }

    public ObjectProperty<FormatStyle> dateFormatStyleProperty() {
        return cellPane.dateFormatStyleProperty();
    }

    public void setDateFormatStyle(FormatStyle dateTimeFormatter) {
        cellPane.setDateFormatStyle(dateTimeFormatter);
    }
}
