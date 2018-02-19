package org.pcsoft.framework.jfex.component.cell;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class LocalDateTimeCellPaneViewModel implements ViewModel {

    private final StringProperty date = new SimpleStringProperty(), time = new SimpleStringProperty();

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void updateDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            date.set("");
            time.set("");
        } else {
            date.set(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
            time.set(localDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
        }
    }
}
