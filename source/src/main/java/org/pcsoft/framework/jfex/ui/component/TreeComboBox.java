package org.pcsoft.framework.jfex.ui.component;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TreeItem;
import org.pcsoft.framework.jfex.util.FXListUtils;

import java.util.List;


public class TreeComboBox<T> extends ComboBox<TreeItem<T>> {

    protected static final int SPACE = 20;

    private final BooleanProperty showRoot = new SimpleBooleanProperty(true);
    private final ObjectProperty<TreeItem<T>> root = new SimpleObjectProperty<>(null);
    private final DoubleProperty intent = new SimpleDoubleProperty(SPACE);

    public TreeComboBox() {
        root.addListener((v, o, n) -> rebuildItems(n));
        setCellFactory(param -> new ListCell<TreeItem<T>>() {
            @Override
            protected void updateItem(TreeItem<T> item, boolean empty) {
                super.updateItem(item, empty);

                setText(null);
                setGraphic(null);
                if (item != null && !empty) {
                    setPadding(new Insets(2, 2, 2, 2 + calculateLevel(item) * intent.get()));
                    setGraphic(item.getGraphic());
                    if (getConverter() == null) {
                        setText(item.getValue().toString());
                    } else {
                        setText(getConverter().toString(item));
                    }
                }
            }
        });
        setButtonCell(new ListCell<TreeItem<T>>() {
            @Override
            protected void updateItem(TreeItem<T> item, boolean empty) {
                super.updateItem(item, empty);

                setText(null);
                setGraphic(null);
                if (item != null && !empty) {
                    setGraphic(item.getGraphic());
                    if (getConverter() == null) {
                        setText(item.getValue().toString());
                    } else {
                        setText(getConverter().toString(item));
                    }
                }
            }
        });
        setConverter(null);

        intent.addListener(o -> FXListUtils.reselect(this));
    }

    private void rebuildItems(TreeItem<T> item) {
        super.getItems().clear();
        if (showRoot.get()) {
            super.getItems().add(item);
        }
        rebuildNextItems(item.getChildren(), showRoot.get() ? 1 : 0);
    }

    private void rebuildNextItems(List<TreeItem<T>> itemList, int level) {
        for (final TreeItem<T> item : itemList) {
            super.getItems().add(item);
            rebuildNextItems(item.getChildren(), level + 1);
        }
    }

    public boolean getShowRoot() {
        return showRoot.get();
    }

    public BooleanProperty showRootProperty() {
        return showRoot;
    }

    public void setShowRoot(boolean showRoot) {
        this.showRoot.set(showRoot);
    }

    public TreeItem<T> getRoot() {
        return root.get();
    }

    public ObjectProperty<TreeItem<T>> rootProperty() {
        return root;
    }

    public void setRoot(TreeItem<T> root) {
        this.root.set(root);
    }

    public double getIntent() {
        return intent.get();
    }

    public DoubleProperty intentProperty() {
        return intent;
    }

    public void setIntent(double intent) {
        this.intent.set(intent);
    }

    protected static <T> int calculateLevel(final TreeItem<T> treeItem) {
        int counter=0;
        TreeItem current = treeItem.getParent();
        while (current != null) {
            counter++;
            current = current.getParent();
        }

        return counter;
    }
}
