package org.pcsoft.framework.jfex.controls.util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EventHandlerTestRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TextField txtInt = new TextField();
        txtInt.addEventHandler(KeyEvent.KEY_TYPED, EventHandlerUtils.TextFieldHandlers.createNumericIntegerInputRestrictionHandler());
        final TextField txtDecimal = new TextField();
        txtDecimal.addEventHandler(KeyEvent.KEY_TYPED, EventHandlerUtils.TextFieldHandlers.createNumericDecimalInputRestrictionHandler());

        primaryStage.setScene(new Scene(new VBox(5, txtInt, txtDecimal), 800, 600));
        primaryStage.show();
    }
}
