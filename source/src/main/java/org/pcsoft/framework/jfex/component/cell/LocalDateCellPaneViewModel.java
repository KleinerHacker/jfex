package org.pcsoft.framework.jfex.component.cell;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class LocalDateCellPaneViewModel implements ViewModel {

    private final StringProperty date = new SimpleStringProperty();

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void updateDate(LocalDate localDate) {
        if (localDate == null) {
            date.set("");
        } else {
            date.set(localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        }
    }
}
