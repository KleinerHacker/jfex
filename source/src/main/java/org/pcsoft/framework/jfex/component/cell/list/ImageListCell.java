package org.pcsoft.framework.jfex.component.cell.list;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.function.Function;


public class ImageListCell<T> extends ListCell<T> {
    private final ObjectProperty<Function<T, Image>> imageFunction = new SimpleObjectProperty<>();
    private final BooleanProperty showImageIfNull = new SimpleBooleanProperty(true);

    public ImageListCell(final Function<T, Image> imageFunction, final boolean showImageIfNull) {
        this.imageFunction.set(imageFunction);
        this.showImageIfNull.set(showImageIfNull);
    }

    public ImageListCell(final Function<T, Image> imageFunction) {
        this.imageFunction.set(imageFunction);
    }

    public ImageListCell(final Image image, final boolean showImageIfNull) {
        this.imageFunction.set(o -> image);
        this.showImageIfNull.set(showImageIfNull);
    }

    public ImageListCell(final Image image) {
        this.imageFunction.set(o -> image);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        setGraphic(null);
        setText(null);
        if (!empty) {
            if (showImageIfNull.get() || item != null) {
                if (imageFunction.get() != null) {
                    setGraphic(new ImageView(imageFunction.get().apply(item)));
                }
            }
        }
    }

    public Function<T, Image> getImageFunction() {
        return imageFunction.get();
    }

    public ObjectProperty<Function<T, Image>> imageFunctionProperty() {
        return imageFunction;
    }

    public void setImageFunction(Function<T, Image> imageFunction) {
        this.imageFunction.set(imageFunction);
    }

    public boolean getShowImageIfNull() {
        return showImageIfNull.get();
    }

    public BooleanProperty showImageIfNullProperty() {
        return showImageIfNull;
    }

    public void setShowImageIfNull(boolean showImageIfNull) {
        this.showImageIfNull.set(showImageIfNull);
    }
}
