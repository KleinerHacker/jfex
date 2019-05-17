package org.pcsoft.framework.jfex.commons.converter;

import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;


public class FloatPercentageConverter extends StringConverter<Float> {

    @Override
    public String toString(Float object) {
        return object == null ? "100%" : (int) (object * 100f) + "%";
    }

    @Override
    public Float fromString(String string) {
        return StringUtils.isEmpty(string) ? 1f : Float.parseFloat(string.replace("%", "").trim()) / 100f;
    }
}
