package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jfex.io.UiStateStorage;
import org.pcsoft.framework.jfex.type.ChooserType;

import java.io.File;


public class FileChooserPaneViewModel implements ViewModel {
    private static final String KEY_LAST_DIR = "file.chooser.last.dir.%s";

    private final ObjectProperty<File> file = new SimpleObjectProperty<>();
    private final ObjectProperty<FileChooserPane.ViewType> viewType = new SimpleObjectProperty<>(FileChooserPane.ViewType.WithTextBox);
    private final ReadOnlyListProperty<FileChooser.ExtensionFilter> extensionFilterList =
            new ReadOnlyListWrapper<FileChooser.ExtensionFilter>(FXCollections.observableArrayList()).getReadOnlyProperty();
    private final StringProperty dialogTitle = new SimpleStringProperty(), prompt = new SimpleStringProperty();
    private final BooleanProperty storeLastDirectory = new SimpleBooleanProperty(false);
    private final StringProperty storeLastDirectoryKey = new SimpleStringProperty();
    private final BooleanProperty clearFileAvailable = new SimpleBooleanProperty(true);
    private final ObjectProperty<ChooserType> dialogType = new SimpleObjectProperty<>(ChooserType.FileOpen);
    private final BooleanProperty showAlwaysAllFilesFilter = new SimpleBooleanProperty(true);

    private final StringBinding filename;
    private final BooleanBinding showTextBox, showLabel;
    private final StringBinding textBoxStyle;

    public FileChooserPaneViewModel() {
        filename = Bindings.when(file.isNull()).then("").otherwise(file.asString());
        showTextBox = viewType.isEqualTo(FileChooserPane.ViewType.WithTextBox).or(viewType.isEqualTo(FileChooserPane.ViewType.WithFlatTextBox));
        showLabel = viewType.isEqualTo(FileChooserPane.ViewType.WithLabel);
        textBoxStyle = Bindings.when(viewType.isEqualTo(FileChooserPane.ViewType.WithFlatTextBox)).then("-fx-background-color: transparent").otherwise("");
    }

    @Override
    protected void finalize() throws Throwable {
        UiStateStorage.getInstance().getSystemPropertyGroup().unregisterProperty(buildKey(storeLastDirectoryKey.get()));
        super.finalize();
    }

    public boolean isShowAlwaysAllFilesFilter() {
        return showAlwaysAllFilesFilter.get();
    }

    public BooleanProperty showAlwaysAllFilesFilterProperty() {
        return showAlwaysAllFilesFilter;
    }

    public void setShowAlwaysAllFilesFilter(boolean showAlwaysAllFilesFilter) {
        this.showAlwaysAllFilesFilter.set(showAlwaysAllFilesFilter);
    }

    public ChooserType getDialogType() {
        return dialogType.get();
    }

    public ObjectProperty<ChooserType> dialogTypeProperty() {
        return dialogType;
    }

    public void setDialogType(ChooserType dialogType) {
        this.dialogType.set(dialogType);
    }

    public String getStoreLastDirectoryKey() {
        return storeLastDirectoryKey.get();
    }

    public StringProperty storeLastDirectoryKeyProperty() {
        return storeLastDirectoryKey;
    }

    public void setStoreLastDirectoryKey(String storeLastDirectoryKey) {
        this.storeLastDirectoryKey.set(storeLastDirectoryKey);
    }

    public boolean isClearFileAvailable() {
        return clearFileAvailable.get();
    }

    public BooleanProperty clearFileAvailableProperty() {
        return clearFileAvailable;
    }

    public void setClearFileAvailable(boolean clearFileAvailable) {
        this.clearFileAvailable.set(clearFileAvailable);
    }

    public Boolean getShowTextBox() {
        return showTextBox.get();
    }

    public BooleanBinding showTextBoxProperty() {
        return showTextBox;
    }

    public Boolean getShowLabel() {
        return showLabel.get();
    }

    public BooleanBinding showLabelProperty() {
        return showLabel;
    }

    public String getTextBoxStyle() {
        return textBoxStyle.get();
    }

    public StringBinding textBoxStyleProperty() {
        return textBoxStyle;
    }

    public File getFile() {
        return file.get();
    }

    public ObjectProperty<File> fileProperty() {
        return file;
    }

    public void setFile(File file) {
        this.file.set(file);
    }

    public FileChooserPane.ViewType getViewType() {
        return viewType.get();
    }

    public ObjectProperty<FileChooserPane.ViewType> viewTypeProperty() {
        return viewType;
    }

    public void setViewType(FileChooserPane.ViewType viewType) {
        this.viewType.set(viewType);
    }

    public ObservableList<FileChooser.ExtensionFilter> getExtensionFilterList() {
        return extensionFilterList.get();
    }

    public ReadOnlyListProperty<FileChooser.ExtensionFilter> extensionFilterListProperty() {
        return extensionFilterList;
    }

    public String getDialogTitle() {
        return dialogTitle.get();
    }

    public StringProperty dialogTitleProperty() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle.set(dialogTitle);
    }

    public String getPrompt() {
        return prompt.get();
    }

    public StringProperty promptProperty() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt.set(prompt);
    }

    public boolean isStoreLastDirectory() {
        return storeLastDirectory.get();
    }

    public BooleanProperty storeLastDirectoryProperty() {
        return storeLastDirectory;
    }

    public void setStoreLastDirectory(boolean storeLastDirectory) {
        this.storeLastDirectory.set(storeLastDirectory);
    }

    public String getFilename() {
        return filename.get();
    }

    public StringBinding filenameProperty() {
        return filename;
    }

    protected String buildKey(String baseKey) {
        return String.format(KEY_LAST_DIR, StringUtils.defaultIfEmpty(baseKey, "unnamed"));
    }
}
