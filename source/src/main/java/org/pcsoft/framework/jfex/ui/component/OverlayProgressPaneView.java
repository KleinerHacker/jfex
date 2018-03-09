package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.FxmlView;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;


public abstract class OverlayProgressPaneView<M extends OverlayProgressPaneViewModel> implements FxmlView<M>, Initializable {
    private static enum FadeState {
        FadeIn,
        FadeOut
    }

    private static final Object FADE_STATE = new Object();
    private FadeState fadeState = null;

    protected void initialize(ProgressIndicator pbProgress, Label lblProgress, Node pnlProgress, M viewModel) {
        pbProgress.progressProperty().bind(viewModel.progressProperty());
        lblProgress.textProperty().bind(viewModel.actionProperty());
        viewModel.visibleProperty().addListener((v, o, n) -> {
            if (n) {
                fadeIn(pnlProgress);
            } else {
                fadeOut(pnlProgress);
            }
        });

        pnlProgress.setOpacity(0);
        pnlProgress.setVisible(false);
    }

    protected void fadeIn(final Node pnlProgress) {
        if (!Platform.isFxApplicationThread())
            throw new IllegalStateException("FadeIn call runs not on FX Thread");

        synchronized (FADE_STATE) {
            if (fadeState == FadeState.FadeIn)
                return;
            fadeState = FadeState.FadeIn;
        }

        try {
            pnlProgress.setOpacity(0);
            pnlProgress.setVisible(true);

            final FadeTransition fadeTransition = new FadeTransition(new Duration(300), pnlProgress);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setOnFinished(e -> {
                synchronized (FADE_STATE) {
                    fadeState = null;
                }
            });
            fadeTransition.play();
        } catch (Exception e) {
            synchronized (FADE_STATE) {
                fadeState = null;
            }
            throw e;
        }
    }

    protected void fadeOut(final Node pnlProgress) {
        if (!Platform.isFxApplicationThread())
            throw new IllegalStateException("FadeOut call runs not on FX Thread");

        synchronized (FADE_STATE) {
            if (fadeState == FadeState.FadeOut)
                return;
            fadeState = FadeState.FadeOut;
        }

        try {
            pnlProgress.setOpacity(1);
            pnlProgress.setVisible(true);

            final FadeTransition fadeTransition = new FadeTransition(new Duration(300), pnlProgress);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setOnFinished(e -> {
                pnlProgress.setVisible(false);
                synchronized (FADE_STATE) {
                    fadeState = null;
                }
            });
            fadeTransition.play();
        } catch (Exception e) {
            synchronized (FADE_STATE) {
                fadeState = null;
            }
            throw e;
        }
    }

}
