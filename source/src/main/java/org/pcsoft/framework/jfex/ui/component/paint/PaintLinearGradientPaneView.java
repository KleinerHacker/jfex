package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintLinearGradientPaneView implements FxmlView<PaintLinearGradientPaneViewModel>, Initializable {
    @FXML
    private Slider sldStartX;
    @FXML
    private Slider sldStartY;
    @FXML
    private Slider sldEndX;
    @FXML
    private Slider sldEndY;
    @FXML
    private GradientStopTable tblStops;
    @FXML
    private Rectangle rectPreview;

    @InjectViewModel
    private PaintLinearGradientPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rectPreview.fillProperty().bind(viewModel.linearGradientProperty());

        viewModel.linearGradientProperty().bind(Bindings.createObjectBinding(
                () -> new LinearGradient(sldStartX.getValue(), sldStartY.getValue(), sldEndX.getValue(), sldEndY.getValue(),
                        true, CycleMethod.NO_CYCLE, tblStops.getStopList()),
                sldStartX.valueProperty(), sldStartY.valueProperty(), sldEndX.valueProperty(), sldEndY.valueProperty(),
                tblStops.stopListProperty()
        ));
    }
}
