package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintRadialGradientPaneView implements FxmlView<PaintRadialGradientPaneViewModel>, Initializable {
    @FXML
    private Slider sldRadius;
    @FXML
    private Slider sldFocusAngle;
    @FXML
    private Slider sldDistanceAngle;
    @FXML
    private Slider sldPivotX;
    @FXML
    private Slider sldPivotY;
    @FXML
    private GradientStopTable tblStops;
    @FXML
    private Rectangle rectPreview;

    @InjectViewModel
    private PaintRadialGradientPaneViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rectPreview.fillProperty().bind(viewModel.radialGradientProperty());

        viewModel.radialGradientProperty().bind(Bindings.createObjectBinding(
                () -> new RadialGradient(sldFocusAngle.getValue(), sldDistanceAngle.getValue(), sldPivotX.getValue(), sldPivotY.getValue(),
                        sldRadius.getValue(), true, CycleMethod.NO_CYCLE, tblStops.getStopList()),
                sldFocusAngle.valueProperty(), sldDistanceAngle.valueProperty(), sldPivotX.valueProperty(), sldPivotY.valueProperty(),
                tblStops.stopListProperty(), sldRadius.valueProperty()
        ));
    }
}
