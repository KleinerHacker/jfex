package org.pcsoft.framework.jfex.controls.ui.component.paint;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import org.pcsoft.framework.jfex.commons.property.ExtendedWrapperProperty;
import org.pcsoft.framework.jfex.mvvm.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintImagePaneView extends FxmlView<PaintImagePaneViewModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaintImagePaneView.class);

    @FXML
    private ImageView imgValue;
    @FXML
    private Button btnChoose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.imagePatternProperty().bindBidirectional(new ExtendedWrapperProperty<ImagePattern>(imgValue.imageProperty()) {
            @Override
            protected ImagePattern getPseudoValue() {
                return imgValue.getImage() == null ? null : new ImagePattern(imgValue.getImage());
            }

            @Override
            protected void setPseudoValue(ImagePattern value) {
                imgValue.setImage(value.getImage());
            }
        });
    }

    @FXML
    private void onActionChoose(Event e) {
        if (viewModel.getImageChooserCallback() == null)
            throw new IllegalStateException("Image Chooser Callback is NULL");

        imgValue.setImage(viewModel.getImageChooserCallback().get());
    }
}
