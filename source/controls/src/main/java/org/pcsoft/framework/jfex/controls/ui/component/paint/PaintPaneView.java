package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.util.StringConverter;
import org.pcsoft.framework.jfex.commons.property.ExtendedWrapperProperty;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintPaneView extends FxmlView<PaintPaneViewModel> {
    @FXML
    private BorderPane pnlRoot;
    @FXML
    private ChoiceBox<PaintPaneViewModel.PaintType> cmbPaint;

    private final PaintColorPane pnlColor = new PaintColorPane();
    private final PaintImagePane pnlImage = new PaintImagePane();
    private final PaintLinearGradientPane pnlLinearGradient = new PaintLinearGradientPane();
    private final PaintRadialGradientPane pnlRadialGradient = new PaintRadialGradientPane();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pnlImage.imageChooserCallbackProperty().bind(viewModel.imageChooserCallbackProperty());

        cmbPaint.getItems().setAll(PaintPaneViewModel.PaintType.values());
        cmbPaint.setConverter(new StringConverter<PaintPaneViewModel.PaintType>() {
            @Override
            public String toString(PaintPaneViewModel.PaintType object) {
                return object.getName();
            }

            @Override
            public PaintPaneViewModel.PaintType fromString(String string) {
                for (final PaintPaneViewModel.PaintType paintType : PaintPaneViewModel.PaintType.values()) {
                    if (paintType.getName().equals(string))
                        return paintType;
                }

                return null;
            }
        });
        cmbPaint.valueProperty().addListener((v, o, n) -> {
            pnlRoot.setCenter(null);
            if (n == null)
                return;

            switch (n) {
                case Color:
                    pnlRoot.setCenter(pnlColor);
                    break;
                case Image:
                    pnlRoot.setCenter(pnlImage);
                    break;
                case LinearGradient:
                    pnlRoot.setCenter(pnlLinearGradient);
                    break;
                case RadialGradient:
                    pnlRoot.setCenter(pnlRadialGradient);
                    break;
                default:
                    throw new RuntimeException();
            }
        });

        viewModel.selectedPaintProperty().bindBidirectional(
                new ExtendedWrapperProperty<Paint>(cmbPaint.valueProperty(), pnlColor.selectedColorProperty(),
                        pnlImage.imageChooserCallbackProperty(), pnlLinearGradient.linearGradientProperty(),
                        pnlRadialGradient.radialGradientProperty()) {
                    @Override
                    protected Paint getPseudoValue() {
                        if (cmbPaint.getValue() == null)
                            return null;

                        switch (cmbPaint.getValue()) {
                            case Color:
                                //noinspection unchecked
                                return pnlColor.getSelectedColor();
                            case Image:
                                return pnlImage.getImagePattern();
                            case LinearGradient:
                                return pnlLinearGradient.getLinearGradient();
                            case RadialGradient:
                                return pnlRadialGradient.getRadialGradient();
                            default:
                                throw new RuntimeException();
                        }
                    }

                    @Override
                    protected void setPseudoValue(Paint value) {
                        if (value instanceof Color) {
                            cmbPaint.setValue(PaintPaneViewModel.PaintType.Color);
                            pnlColor.setSelectedColor((Color) value);
                        } else if (value instanceof ImagePattern) {
                            cmbPaint.setValue(PaintPaneViewModel.PaintType.Image);
                            pnlImage.setImagePattern((ImagePattern) value);
                        } else if (value instanceof LinearGradient) {
                            cmbPaint.setValue(PaintPaneViewModel.PaintType.LinearGradient);
                            pnlLinearGradient.setLinearGradient((LinearGradient) value);
                        } else if (value instanceof RadialGradient) {
                            cmbPaint.setValue(PaintPaneViewModel.PaintType.RadialGradient);
                            pnlRadialGradient.setRadialGradient((RadialGradient) value);
                        } else
                            throw new RuntimeException();
                    }
                }
        );
    }
}
