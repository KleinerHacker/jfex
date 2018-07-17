package org.pcsoft.framework.jfex.ui.component.cell.list;


import javafx.beans.property.ObjectProperty;
import org.pcsoft.framework.jfex.ui.component.cell.LocalDateCellPane;

import java.time.LocalDate;
import java.time.format.FormatStyle;


public class LocalDateListCell extends ListCellBase<LocalDate, LocalDateCellPane> {
    public LocalDateListCell() {
        super(LocalDateCellPane.class);
    }

    public LocalDateListCell(FormatStyle dateFormatStyle) {
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
