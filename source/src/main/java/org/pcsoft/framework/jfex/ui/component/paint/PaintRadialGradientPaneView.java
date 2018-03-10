package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;
import org.pcsoft.framework.jfex.property.ExtendedWrapperProperty;

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

        viewModel.radialGradientProperty().bindBidirectional(
                new ExtendedWrapperProperty<RadialGradient>(sldFocusAngle.valueProperty(), sldDistanceAngle.valueProperty(),
                        sldPivotX.valueProperty(), sldPivotY.valueProperty(), tblStops.stopListProperty(), sldRadius.valueProperty()) {
                    @Override
                    protected RadialGradient getPseudoValue() {
                        return new RadialGradient(sldFocusAngle.getValue(), sldDistanceAngle.getValue(), sldPivotX.getValue(), sldPivotY.getValue(),
                                sldRadius.getValue(), true, CycleMethod.NO_CYCLE, tblStops.getStopList());
                    }

                    @Override
                    protected void setPseudoValue(RadialGradient value) {
                        sldFocusAngle.setValue(value.getFocusAngle());
                        sldDistanceAngle.setValue(value.getFocusDistance());
                        sldPivotX.setValue(value.getCenterX());
                        sldPivotY.setValue(value.getCenterY());
                        sldRadius.setValue(value.getRadius());
                        tblStops.getStopList().setAll(value.getStops());
                    }
                }
        );
    }
}
