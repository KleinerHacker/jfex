package org.pcsoft.framework.jfex.converter;

import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;


public class DoublePercentageConverter extends StringConverter<Double> {

    @Override
    public String toString(Double object) {
        return object == null ? "100%" : (int) (object * 100d) + "%";
    }

    @Override
    public Double fromString(String string) {
        return StringUtils.isEmpty(string) ? 1f : Double.parseDouble(string.replace("%", "").trim()) / 100d;
    }
}
