package org.pcsoft.framework.jfex.converter;

import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.util.HexUtils;


public class HexConverter extends StringConverter<byte[]> {

    @Override
    public String toString(byte[] object) {
        return HexUtils.toHexString(object);
    }

    @Override
    public byte[] fromString(String string) {
        return HexUtils.toByteArray(string);
    }
}
