package org.pcsoft.framework.jfex.util;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.util.function.Consumer;


public final class FXWindowUtils extends FXUtils {

    public static void handleOnShownWindow(final Node node, final EventHandler<WindowEvent> handler) {
        handleOnWindow(node, w -> w.setOnShown(null), w -> w.setOnShown(handler));
    }

    public static void handleOnCloseWindow(final Node node, final EventHandler<WindowEvent> handler) {
        handleOnWindow(node, w -> w.setOnCloseRequest(null), w -> w.setOnCloseRequest(handler));
    }

    public static void setWindowIcons(final Node node, final Image... icons) {
        handleOnWindow(node, w -> ((Stage)w).getIcons().clear(), w -> ((Stage)w).getIcons().setAll(icons));
    }

    private static void handleOnWindow(final Node node, final Consumer<Window> cleanupConsumer, final Consumer<Window> setupConsumer) {
        ChangeListener<Window> changeListener = (v, o, n) -> {
            if (o != null) {
                cleanupConsumer.accept(o);
            }

            if (n == null)
                return;

            setupConsumer.accept(n);
        };

        node.sceneProperty().addListener((v, o, n) -> {
            if (o != null) {
                o.windowProperty().removeListener(changeListener);
            }

            if (n != null) {
                n.windowProperty().addListener(changeListener);
                changeListener.changed(n.windowProperty(), n.getWindow(), n.getWindow());
            }
        });
    }

    private FXWindowUtils() {
    }
}
