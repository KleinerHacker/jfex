package org.pcsoft.framework.jfex.workflow.component;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;


public class ArrowLine extends Group {
    private final DoubleProperty startX = new SimpleDoubleProperty(), startY = new SimpleDoubleProperty(),
            endX = new SimpleDoubleProperty(), endY = new SimpleDoubleProperty(), arrowSize = new SimpleDoubleProperty(10);
    private final DoubleBinding angle;

    public ArrowLine() {
        angle = Bindings.createDoubleBinding(
                () -> Math.toDegrees(Math.atan2(endY.get() - startY.get(), endX.get() - startX.get())),
                startX, startY, endX, endY
        );

        final Line line = new Line();
        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);
        getChildren().add(line);

        final Polyline arrow = new Polyline();
        final Rotate rotate = new Rotate();
        rotate.angleProperty().bind(angle.subtract(90));
        rotate.pivotXProperty().bind(endX);
        rotate.pivotYProperty().bind(endY);
        arrow.getTransforms().add(rotate);
        final InvalidationListener invalidationListener = o -> {
            arrow.getPoints().setAll(
                    endX.get() - arrowSize.get() / 2, endY.get() - arrowSize.get(),
                    endX.get(), endY.get(),
                    endX.get() + arrowSize.get() / 2, endY.get() - arrowSize.get());
        };
        arrowSize.addListener(invalidationListener);
        endX.addListener(invalidationListener);
        endY.addListener(invalidationListener);
        getChildren().add(arrow);
    }

    public ArrowLine(final double arrowSize) {
        this();
        setArrowSize(arrowSize);
    }

    public ArrowLine(final double startX, final double startY, final double endX, final double endY, final double arrowSize) {
        this(arrowSize);
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
    }

    public double getStartX() {
        return startX.get();
    }

    public DoubleProperty startXProperty() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX.set(startX);
    }

    public double getStartY() {
        return startY.get();
    }

    public DoubleProperty startYProperty() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY.set(startY);
    }

    public double getEndX() {
        return endX.get();
    }

    public DoubleProperty endXProperty() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX.set(endX);
    }

    public double getEndY() {
        return endY.get();
    }

    public DoubleProperty endYProperty() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY.set(endY);
    }

    public double getArrowSize() {
        return arrowSize.get();
    }

    public DoubleProperty arrowSizeProperty() {
        return arrowSize;
    }

    public void setArrowSize(double arrowSize) {
        this.arrowSize.set(arrowSize);
    }
}
