package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jfex.commons.util.Base64Utils;
import org.pcsoft.framework.jfex.commons.util.HexUtils;



public class ByteArrayStringProperty extends SimpleWrapperProperty<String, byte[]> {

    public enum StringRepresentation {
        Hex,
        Base64
    }

    private final ObjectProperty<StringRepresentation> stringRepresentation = new SimpleObjectProperty<>();

    public ByteArrayStringProperty(ObjectProperty<byte[]> byteArray) {
        this(byteArray, StringRepresentation.Hex);
    }

    public ByteArrayStringProperty(ObjectProperty<byte[]> byteArray, StringRepresentation stringRepresentation) {
        super(byteArray);
        this.stringRepresentation.set(stringRepresentation);

        byteArray.addListener((v, o, n) -> fireValueChangedEvent());
    }

    public StringRepresentation getStringRepresentation() {
        return stringRepresentation.get();
    }

    public ObjectProperty<StringRepresentation> stringRepresentationProperty() {
        return stringRepresentation;
    }

    public void setStringRepresentation(StringRepresentation stringRepresentation) {
        this.stringRepresentation.set(stringRepresentation);
    }

    @Override
    protected String convertTo(byte[] value) {
        switch (stringRepresentation.get()) {
            case Base64:
                return Base64Utils.toBase64(value);
            case Hex:
                return HexUtils.toHexString(value);
            default:
                throw new IllegalStateException("not implemented: " + stringRepresentation);
        }
    }

    @Override
    protected byte[] convertFrom(String value) {
        switch (stringRepresentation.get()) {
            case Hex:
                return HexUtils.toByteArray(value);
            case Base64:
                return Base64Utils.toByteArray(value);
            default:
                throw new IllegalStateException("not implemented: " + stringRepresentation);
        }
    }
}
