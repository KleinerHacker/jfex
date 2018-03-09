package org.pcsoft.framework.jfex.ui.component.paint;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintPaneView implements FxmlView<PaintPaneViewModel>, Initializable {
    @FXML
    private BorderPane pnlRoot;
    @FXML
    private ChoiceBox<PaintPaneViewModel.PaintType> cmbPaint;

    @InjectViewModel
    private PaintPaneViewModel viewModel;

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

        viewModel.selectedPaintProperty().bind(Bindings.createObjectBinding(
                () -> {
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
                },
                cmbPaint.valueProperty(), pnlColor.selectedColorProperty()
        ));
    }
}
