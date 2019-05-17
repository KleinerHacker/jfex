package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class HeaderPaneView extends FxmlView<HeaderPaneViewModel> {

    @FXML
    private ImageView imgImage;
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblDescription;

    @FXML
    private Button btnHelp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.textProperty().bind(viewModel.titleProperty());
        lblDescription.textProperty().bind(viewModel.descriptionProperty());
        imgImage.imageProperty().bind(viewModel.imageProperty());

        btnHelp.visibleProperty().bind(viewModel.helpIconVisibleProperty());
        final ImageView imageView = new ImageView();
        imageView.imageProperty().bind(viewModel.helpIconBindingProperty());
        btnHelp.setGraphic(imageView);
        btnHelp.onActionProperty().bind(viewModel.onHelpActionProperty());
    }
}
