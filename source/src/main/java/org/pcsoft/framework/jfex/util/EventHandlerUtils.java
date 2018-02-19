package org.pcsoft.framework.jfex.util;

import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public final class EventHandlerUtils {

    public static final class TextFieldHandlers {
        public static EventHandler<KeyEvent> createNumericIntegerInputRestrictionHandler() {
            return createNumericIntegerInputRestrictionHandler(-1);
        }

        public static EventHandler<KeyEvent> createNumericIntegerInputRestrictionHandler(final int maxLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[\\-0-9]+")) {
                    event.consume();
                } else {
                    final TextField source = (TextField) event.getSource();

                    if (maxLength >= 0 && source.getText() != null && source.getText().length() >= maxLength) {
                        event.consume();
                    } else {
                        try {
                            final String value = StringUtilsEx.insert(source.getText(), event.getCharacter(), source.getCaretPosition());
                            Integer.parseInt(value);
                        } catch (NumberFormatException ignore) {
                            event.consume();
                        }
                    }
                }
            };
        }

        public static EventHandler<KeyEvent> createNumericDecimalInputRestrictionHandler() {
            return createNumericIntegerInputRestrictionHandler(-1);
        }

        public static EventHandler<KeyEvent> createNumericDecimalInputRestrictionHandler(final int maxLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[\\-0-9\\.,]+")) {
                    event.consume();
                } else {
                    final TextField source = (TextField) event.getSource();

                    if (maxLength >= 0 && source.getText() != null && source.getText().length() >= maxLength) {
                        event.consume();
                    } else {
                        try {
                            final String value = StringUtilsEx.insert(source.getText(), event.getCharacter(), source.getCaretPosition());
                            Double.parseDouble(value);
                        } catch (NumberFormatException ignore) {
                            event.consume();
                        }
                    }
                }
            };
        }

        public static EventHandler<KeyEvent> createLengthInputRestrictionHandler(final int maxLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (((TextField) event.getSource()).getText().length() >= maxLength) {
                    event.consume();
                }
            };
        }

        public static EventHandler<KeyEvent> createHexInputRestrictionHandler() {
            return createHexInputRestrictionHandler(-1);
        }

        public static EventHandler<KeyEvent> createHexInputRestrictionHandler(final int byteLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[0-9a-fA-F]+")) {
                    event.consume();
                } else if (((TextField) event.getSource()).getText() != null && byteLength >= 0 && ((TextField) event.getSource()).getText().length() >= byteLength * 2) {
                    event.consume();
                }
            };
        }

        public static EventHandler<KeyEvent> createOIDInputRestrictionHandler() {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[0-9\\.]+")) {
                    event.consume();
                }
            };
        }

        private TextFieldHandlers() {
        }
    }

    public static final class SpinnerHandlers {
        public static EventHandler<KeyEvent> createNumericIntegerInputRestrictionHandler() {
            return createNumericIntegerInputRestrictionHandler(-1);
        }

        public static EventHandler<KeyEvent> createNumericIntegerInputRestrictionHandler(final int maxLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[\\-0-9]+")) {
                    event.consume();
                } else if (maxLength >= 0 && ((Spinner) event.getSource()).getEditor().getText() != null && ((Spinner) event.getSource()).getEditor().getText().length() >= maxLength) {
                    event.consume();
                } else {
                    try {
                        final Spinner source = (Spinner) event.getSource();

                        final String value = StringUtilsEx.insert(source.getEditor().getText(), event.getCharacter(), source.getEditor().getCaretPosition());
                        Integer.parseInt(value);
                    } catch (NumberFormatException ignore) {
                        event.consume();
                    }
                }
            };
        }

        public static EventHandler<KeyEvent> createNumericDecimalInputRestrictionHandler() {
            return createNumericIntegerInputRestrictionHandler(-1);
        }

        public static EventHandler<KeyEvent> createNumericDecimalInputRestrictionHandler(final int maxLength) {
            return event -> {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE ||
                        event.getCode().isNavigationKey())
                    return;

                if (!event.getCharacter().matches("[\\-0-9\\.,]+")) {
                    event.consume();
                } else if (maxLength >= 0 && ((Spinner) event.getSource()).getEditor().getText() != null && ((Spinner) event.getSource()).getEditor().getText().length() >= maxLength) {
                    event.consume();
                } else {
                    try {
                        final Spinner source = (Spinner) event.getSource();

                        final String value = StringUtilsEx.insert(source.getEditor().getText(), event.getCharacter(), source.getEditor().getCaretPosition());
                        Double.parseDouble(value);
                    } catch (NumberFormatException ignore) {
                        event.consume();
                    }
                }
            };
        }

        private SpinnerHandlers() {
        }
    }

    private EventHandlerUtils() {
    }
}
