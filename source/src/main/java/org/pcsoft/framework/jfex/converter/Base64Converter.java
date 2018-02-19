package org.pcsoft.framework.jfex.converter;

import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.util.Base64Utils;


public class Base64Converter extends StringConverter<byte[]> {

    @Override
    public String toString(byte[] object) {
        return Base64Utils.toBase64(object);
    }

    @Override
    public byte[] fromString(String string) {
        return Base64Utils.toByteArray(string);
    }
}
