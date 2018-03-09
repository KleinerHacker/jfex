package org.pcsoft.framework.jfex.ui.dialog;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.pcsoft.framework.jfex.ui.component.HeaderPane;

import java.util.ResourceBundle;

public class GradientStopDialog extends Dialog<GradientStopDialog.Data> {

    public static final class Data {
        private final double offset;
        private final Color color;

        public Data(double offset, Color color) {
            this.offset = offset;
            this.color = color;
        }

        public double getOffset() {
            return offset;
        }

        public Color getColor() {
            return color;
        }
    }

    private final GradientStopDialogViewModel viewModel;
    private final GradientStopDialogView controller;

    public GradientStopDialog(final Window owner) {
        this(owner, null);
    }

    public GradientStopDialog(final Window owner, final Data data) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("lan/text");

        initOwner(owner);
        initStyle(StageStyle.UTILITY);
        setTitle(resourceBundle.getString("dlg.gradient.stop.title"));
        setResizable(false);
        getDialogPane().setHeader(new HeaderPane(
                resourceBundle.getString("dlg.gradient.stop.title"),
                resourceBundle.getString("dlg.gradient.stop.description"),
                new Image(getClass().getResourceAsStream("/icons/ic_gradient_stop48.png"))
        ));
        getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        final ViewTuple<GradientStopDialogView, GradientStopDialogViewModel> viewTuple =
                FluentViewLoader.fxmlView(GradientStopDialogView.class).resourceBundle(resourceBundle).load();
        viewModel = viewTuple.getViewModel();
        controller = viewTuple.getCodeBehind();

        if (data != null) {
            viewModel.setOffset(data.getOffset());
            viewModel.setColor(data.getColor());
        }

        getDialogPane().setContent(viewTuple.getView());
        final Button btnOK = (Button) getDialogPane().lookupButton(ButtonType.OK);
        btnOK.disableProperty().bind(viewModel.allowOkProperty().not());

        setResultConverter(param -> {
            if (param != ButtonType.OK)
                return null;

            return new Data(viewModel.getOffset(), viewModel.getColor());
        });
    }
}
