package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.pcsoft.framework.jfex.controls.type.ChooserType;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.io.File;
import java.util.ResourceBundle;


public class FileChooserPane extends HBox {
    public enum ViewType {
        WithLabel,
        WithTextBox,
        WithFlatTextBox;
    }

    private final FileChooserPaneView controller;
    private final FileChooserPaneViewModel viewModel;

    public FileChooserPane() {
        final Fxml.FxmlTuple<FileChooserPaneView, FileChooserPaneViewModel> viewTuple =
                Fxml.from(FileChooserPaneView.class).withResources(ResourceBundle.getBundle("lan/text")).withRoot(this).load();
        controller = viewTuple.getViewController();
        viewModel = viewTuple.getViewModel();
    }

    public ChooserType getDialogType() {
        return viewModel.getDialogType();
    }

    public ObjectProperty<ChooserType> dialogTypeProperty() {
        return viewModel.dialogTypeProperty();
    }

    public void setDialogType(ChooserType dialogType) {
        viewModel.setDialogType(dialogType);
    }

    public String getStoreLastDirectoryKey() {
        return viewModel.getStoreLastDirectoryKey();
    }

    public StringProperty storeLastDirectoryKeyProperty() {
        return viewModel.storeLastDirectoryKeyProperty();
    }

    public void setStoreLastDirectoryKey(String storeLastDirectoryKey) {
        viewModel.setStoreLastDirectoryKey(storeLastDirectoryKey);
    }

    public boolean isClearFileAvailable() {
        return viewModel.isClearFileAvailable();
    }

    public BooleanProperty clearFileAvailableProperty() {
        return viewModel.clearFileAvailableProperty();
    }

    public void setClearFileAvailable(boolean clearFileAvailable) {
        viewModel.setClearFileAvailable(clearFileAvailable);
    }

    public File getFile() {
        return viewModel.getFile();
    }

    public ObjectProperty<File> fileProperty() {
        return viewModel.fileProperty();
    }

    public void setFile(File file) {
        viewModel.setFile(file);
    }

    public ViewType getViewType() {
        return viewModel.getViewType();
    }

    public ObjectProperty<ViewType> viewTypeProperty() {
        return viewModel.viewTypeProperty();
    }

    public void setViewType(ViewType viewType) {
        viewModel.setViewType(viewType);
    }

    public ObservableList<FileChooser.ExtensionFilter> getExtensionFilterList() {
        return viewModel.getExtensionFilterList();
    }

    public ReadOnlyListProperty<FileChooser.ExtensionFilter> extensionFilterListProperty() {
        return viewModel.extensionFilterListProperty();
    }

    public String getDialogTitle() {
        return viewModel.getDialogTitle();
    }

    public StringProperty dialogTitleProperty() {
        return viewModel.dialogTitleProperty();
    }

    public void setDialogTitle(String dialogTitle) {
        viewModel.setDialogTitle(dialogTitle);
    }

    public String getPrompt() {
        return viewModel.getPrompt();
    }

    public StringProperty promptProperty() {
        return viewModel.promptProperty();
    }

    public void setPrompt(String prompt) {
        viewModel.setPrompt(prompt);
    }

    public boolean isStoreLastDirectory() {
        return viewModel.isStoreLastDirectory();
    }

    public BooleanProperty storeLastDirectoryProperty() {
        return viewModel.storeLastDirectoryProperty();
    }

    public void setStoreLastDirectory(boolean storeLastDirectory) {
        viewModel.setStoreLastDirectory(storeLastDirectory);
    }
}
