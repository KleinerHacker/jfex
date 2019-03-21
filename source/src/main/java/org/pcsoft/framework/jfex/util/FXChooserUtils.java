package org.pcsoft.framework.jfex.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.pcsoft.framework.jfex.io.UiStateStorage;
import org.pcsoft.framework.jfex.type.ChooserType;

import java.io.File;
import java.util.*;

/**
 * Utility Class to show a chooser
 */
public final class FXChooserUtils {

    //region File Save Chooser
    public static Optional<File> showFileSaveChooser(final String title, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileSaveChooser(title, null, null, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileSaveChooser(title, null, null, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileSaveChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileSaveChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final File selectedFile, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileSaveChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final File selectedFile, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileSaveChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<File> showFileSaveChooser(final String title, final File selectedFile, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileSaveChooser(title, selectedFile, storageKey, Arrays.asList(extensionFilters));
    }

    public static Optional<File> showFileSaveChooser(final String title, final File selectedFile, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showChooser(ChooserType.FileSave, title, selectedFile, storageKey, extensionFilters, false).map(list -> list.get(0));
    }
    //endregion

    //region File Open Chooser
    public static Optional<File> showFileOpenChooser(final String title, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenChooser(title, null, null, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenChooser(title, null, null, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final File selectedFile, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final File selectedFile, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<File> showFileOpenChooser(final String title, final File selectedFile, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenChooser(title, selectedFile, storageKey, Arrays.asList(extensionFilters));
    }

    public static Optional<File> showFileOpenChooser(final String title, final File selectedFile, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showChooser(ChooserType.FileOpen, title, selectedFile, storageKey, extensionFilters, false).map(list -> list.get(0));
    }
    //endregion

    //region File Open Multiple Chooser
    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenMultipleChooser(title, null, null, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenMultipleChooser(title, null, null, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenMultipleChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenMultipleChooser(title, null, storageKey, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final File selectedFile, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenMultipleChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final File selectedFile, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showFileOpenMultipleChooser(title, selectedFile, null, extensionFilters);
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final File selectedFile, final String storageKey, final FileChooser.ExtensionFilter... extensionFilters) {
        return showFileOpenMultipleChooser(title, selectedFile, storageKey, Arrays.asList(extensionFilters));
    }

    public static Optional<List<File>> showFileOpenMultipleChooser(final String title, final File selectedFile, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters) {
        return showChooser(ChooserType.FileOpen, title, selectedFile, storageKey, extensionFilters, true);
    }
    //endregion

    //region Directory Chooser

    /**
     * Show a directory chooser with the given title
     *
     * @param title
     * @return
     */
    public static Optional<File> showDirectoryChooser(final String title) {
        return showDirectoryChooser(title, null, null);
    }

    /**
     * Show a directory chooser with the given title
     *
     * @param title
     * @param storageKey Key to store and reload automatically the last directory choice
     * @return
     */
    public static Optional<File> showDirectoryChooser(final String title, final String storageKey) {
        return showDirectoryChooser(title, null, storageKey);
    }

    /**
     * Show a directory chooser with the given title
     *
     * @param title
     * @param selectedFile
     * @return
     */
    public static Optional<File> showDirectoryChooser(final String title, final File selectedFile) {
        return showDirectoryChooser(title, selectedFile, null);
    }

    /**
     * Show a directory chooser with the given title
     *
     * @param title
     * @param selectedFile
     * @param storageKey   Key to store and reload automatically the last directory choice
     * @return
     */
    public static Optional<File> showDirectoryChooser(final String title, final File selectedFile, final String storageKey) {
        return showChooser(ChooserType.Directory, title, selectedFile, storageKey, null, false).map(list -> list.get(0));
    }
    //endregion

    //region Basic Methods
    private static Optional<List<File>> showChooser(final ChooserType chooserType, final String title, final File selectedFile, final String storageKey, final Collection<FileChooser.ExtensionFilter> extensionFilters, boolean multiple) {
        final StringProperty lastDirectoryProperty;
        if (storageKey != null) {
            lastDirectoryProperty = new SimpleStringProperty();
            UiStateStorage.getInstance().getSystemPropertyGroup().registerProperty(storageKey, String.class, lastDirectoryProperty);
        } else {
            lastDirectoryProperty = null;
        }

        if (chooserType == ChooserType.Directory) {
            final File file = handleDirectory(title, selectedFile, storageKey, lastDirectoryProperty);
            return Optional.ofNullable(file == null ? null : Collections.singletonList(file));
        } else if (chooserType == ChooserType.FileOpen || chooserType == ChooserType.FileSave) {
            return Optional.ofNullable(handleFile(chooserType, title, selectedFile, storageKey, extensionFilters, lastDirectoryProperty, multiple));
        } else
            throw new RuntimeException();
    }

    private static List<File> handleFile(ChooserType chooserType, String title, File selectedFile, String storageKey, Collection<FileChooser.ExtensionFilter> extensionFilters, StringProperty lastDirectoryProperty, boolean multiple) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        if (extensionFilters == null || extensionFilters.isEmpty()) {
            fileChooser.getExtensionFilters().setAll(
                    new FileChooser.ExtensionFilter(ResourceBundle.getBundle("lan/text").getString("cmp.file.chooser.dlg.extensions.all"), "*.*")
            );
        } else {
            fileChooser.getExtensionFilters().setAll(extensionFilters);
        }
        if (selectedFile != null && selectedFile.getParentFile().exists() && selectedFile.getParentFile().isDirectory()) {
            fileChooser.setInitialDirectory(selectedFile.getParentFile());
            fileChooser.setInitialFileName(selectedFile.getName());
        } else {
            if (lastDirectoryProperty != null && lastDirectoryProperty.get() != null && new File(lastDirectoryProperty.get()).exists()) {
                fileChooser.setInitialDirectory(new File(lastDirectoryProperty.get()));
            }
        }

        final List<File> fileList;
        switch (chooserType) {
            case FileOpen:
                if (multiple) {
                    fileList = fileChooser.showOpenMultipleDialog(null);
                } else {
                    final File file = fileChooser.showOpenDialog(null);
                    fileList = file == null ? null : Collections.singletonList(file);
                }
                break;
            case FileSave:
                final File file = fileChooser.showSaveDialog(null);
                fileList = file == null ? null : Collections.singletonList(file);
                break;
            default:
                throw new RuntimeException();
        }
        if (fileList != null && lastDirectoryProperty != null) {
            lastDirectoryProperty.set(fileList.get(0).getParentFile().getAbsolutePath());
        }
        if (lastDirectoryProperty != null) {
            UiStateStorage.getInstance().getSystemPropertyGroup().unregisterProperty(storageKey);
        }

        return fileList;
    }

    private static File handleDirectory(String title, File selectedFile, String storageKey, StringProperty lastDirectoryProperty) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        if (selectedFile != null) {
            directoryChooser.setInitialDirectory(selectedFile);
        } else {
            if (lastDirectoryProperty != null && lastDirectoryProperty.get() != null && new File(lastDirectoryProperty.get()).exists()) {
                directoryChooser.setInitialDirectory(new File(lastDirectoryProperty.get()));
            }
        }

        final File file = directoryChooser.showDialog(null);
        if (file != null && lastDirectoryProperty != null) {
            lastDirectoryProperty.set(file.getAbsolutePath());
        }
        if (lastDirectoryProperty != null) {
            UiStateStorage.getInstance().getSystemPropertyGroup().unregisterProperty(storageKey);
        }

        return file;
    }
    //endregion

    private FXChooserUtils() {
    }
}
