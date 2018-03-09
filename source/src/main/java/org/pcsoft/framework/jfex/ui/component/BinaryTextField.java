package org.pcsoft.framework.jfex.ui.component;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.pcsoft.framework.jfex.converter.Base64Converter;
import org.pcsoft.framework.jfex.converter.HexConverter;
import org.pcsoft.framework.jfex.util.EventHandlerUtils;


public class BinaryTextField extends TextField {

    public enum Representation {
        Hexadecimal,
        Base64;
    }

    private final ObjectProperty<byte[]> value = new SimpleObjectProperty<>();
    private final ObjectProperty<Representation> representation = new SimpleObjectProperty<>(Representation.Hexadecimal);
    private final IntegerProperty maxByteCount = new SimpleIntegerProperty(-1);

    private EventHandler<KeyEvent> eventFilter = EventHandlerUtils.TextFieldHandlers.createHexInputRestrictionHandler();

    public BinaryTextField() {
        addEventFilter(KeyEvent.KEY_TYPED, eventFilter);
        Bindings.bindBidirectional(textProperty(), valueProperty(), new HexConverter());

        final InvalidationListener listener = o -> {
            removeEventFilter(KeyEvent.KEY_TYPED, eventFilter);
            if (representation.get() == null || representation.get() == Representation.Hexadecimal) {
                if (maxByteCount.get() <= 0) {
                    eventFilter = EventHandlerUtils.TextFieldHandlers.createHexInputRestrictionHandler();
                } else {
                    eventFilter = EventHandlerUtils.TextFieldHandlers.createHexInputRestrictionHandler(maxByteCount.get());
                }

                addEventFilter(KeyEvent.KEY_TYPED, eventFilter);
            } else if (representation.get() == Representation.Base64) {
                //do nothing
            } else
                throw new IllegalStateException("unknown representation type: " + representation.get().name());
        };
        maxByteCount.addListener(listener);
        representation.addListener(listener);

        representation.addListener((v, o, n) -> {
            if (n == null)
                throw new IllegalArgumentException("Unable to set representation of binary text field to NULL");

            Bindings.unbindBidirectional(textProperty(), valueProperty());
            switch (n) {
                case Base64:
                    Bindings.bindBidirectional(textProperty(), valueProperty(), new Base64Converter());
                    break;
                case Hexadecimal:
                    Bindings.bindBidirectional(textProperty(), valueProperty(), new HexConverter());
                    break;
                default:
                    throw new RuntimeException();
            }
        });
    }

    public int getMaxByteCount() {
        return maxByteCount.get();
    }

    public IntegerProperty maxByteCountProperty() {
        return maxByteCount;
    }

    public void setMaxByteCount(int maxByteCount) {
        this.maxByteCount.set(maxByteCount);
    }

    public byte[] getValue() {
        return value.get();
    }

    public ObjectProperty<byte[]> valueProperty() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value.set(value);
    }

    public Representation getRepresentation() {
        return representation.get();
    }

    public ObjectProperty<Representation> representationProperty() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        this.representation.set(representation);
    }
}
