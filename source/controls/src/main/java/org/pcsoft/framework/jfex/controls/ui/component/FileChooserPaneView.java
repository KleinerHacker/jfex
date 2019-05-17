package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jfex.controls.util.FXChooserUtils;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class FileChooserPaneView extends FxmlView<FileChooserPaneViewModel> {
    private static final String BASE_KEY = "component.chooser";

    @FXML
    private ImageView imgClear;
    @FXML
    private TextField txtFilePlace;
    @FXML
    private Label lblFilePlace;

    private ResourceBundle resources;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;

        txtFilePlace.visibleProperty().bind(viewModel.showTextBoxProperty());
        txtFilePlace.styleProperty().bind(viewModel.textBoxStyleProperty());
        lblFilePlace.visibleProperty().bind(viewModel.showLabelProperty());
        lblFilePlace.styleProperty().bind(Bindings.when(viewModel.fileProperty().isNull()).then("-fx-text-fill: gray").otherwise(""));
        imgClear.visibleProperty().bind(viewModel.clearFileAvailableProperty());

        final StringBinding promptBinding = Bindings.when(viewModel.promptProperty().isNull().or(viewModel.promptProperty().isEmpty()))
                .then(resources.getString("cmp.file.chooser.prompt"))
                .otherwise(viewModel.promptProperty());

        txtFilePlace.textProperty().bind(viewModel.filenameProperty());
        txtFilePlace.promptTextProperty().bind(promptBinding);
        lblFilePlace.textProperty().bind(Bindings.when(viewModel.filenameProperty().isEmpty()).then(promptBinding).otherwise(viewModel.filenameProperty()));
    }

    @FXML
    private void onActionChooseFile(ActionEvent e) {
        final String title = StringUtils.isEmpty(viewModel.getDialogTitle()) ? resources.getString("cmp.file.chooser.dlg.title") : viewModel.getDialogTitle();
        final String storageKey = BASE_KEY + "." + viewModel.getStoreLastDirectoryKey();

        final List<FileChooser.ExtensionFilter> extensionFilterList = new ArrayList<>(viewModel.getExtensionFilterList());
        if (viewModel.isShowAlwaysAllFilesFilter()) {
            extensionFilterList.add(new FileChooser.ExtensionFilter(ResourceBundle.getBundle("lan/text").getString("cmp.file.chooser.dlg.extensions.all"), "*.*"));
        }

        final Optional<File> file;
        switch (viewModel.getDialogType()) {
            case Directory:
                file = FXChooserUtils.showDirectoryChooser(title, viewModel.getFile(), storageKey);
                break;
            case FileOpen:
                file = FXChooserUtils.showFileOpenChooser(title, viewModel.getFile(), storageKey, extensionFilterList);
                break;
            case FileSave:
                file = FXChooserUtils.showFileSaveChooser(title, viewModel.getFile(), storageKey, extensionFilterList);
                break;
            default:
                throw new RuntimeException();
        }

        if (file.isPresent()) {
            viewModel.setFile(file.get());
        }
    }

    @FXML
    private void onClickClear(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
            viewModel.setFile(null);
        }
    }
}
