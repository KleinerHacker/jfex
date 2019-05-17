package org.pcsoft.framework.jfex.controls.ui.component.workflow.utils;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;


public final class CalculatorUtils {
    private static enum HorizontalAlignment {
        LEFT, CENTER, RIGHT
    }

    private static enum VerticalAlignment {
        TOP, CENTER, BOTTOM
    }

    /**
     * Calculate the two points between tow panes to link they with a line, started at bounds1 to bounds2
     *
     * @param bounds1
     * @param bounds2
     * @return Array of points to draw a link line, ergo 2 elements always.
     */
    public static Point2D[] calculateStartEndPointBetweenPanes(final Bounds bounds1, final Bounds bounds2) {
        final HorizontalAlignment horizontalAlignment;
        final VerticalAlignment verticalAlignment;
        final double startX, startY, endX, endY;

        if (bounds1.getMaxX() < bounds2.getMinX()) {
            horizontalAlignment = HorizontalAlignment.LEFT;
        } else if (bounds1.getMinX() > bounds2.getMaxX()) {
            horizontalAlignment = HorizontalAlignment.RIGHT;
        } else {
            horizontalAlignment = HorizontalAlignment.CENTER;
        }

        if (bounds1.getMaxY() < bounds2.getMinY()) {
            verticalAlignment = VerticalAlignment.BOTTOM;
        } else if (bounds1.getMinY() > bounds2.getMaxY()) {
            verticalAlignment = VerticalAlignment.TOP;
        } else {
            verticalAlignment = VerticalAlignment.CENTER;
        }

        switch (horizontalAlignment) {
            case RIGHT:
                startX = bounds1.getMinX();
                endX = bounds2.getMaxX();
                break;
            case LEFT:
                startX = bounds1.getMaxX();
                endX = bounds2.getMinX();
                break;
            case CENTER:
                startX = bounds1.getMinX() + bounds1.getWidth() / 2;
                endX = bounds2.getMinX() + bounds2.getWidth() / 2;
                break;
            default:
                throw new RuntimeException();
        }

        switch (verticalAlignment) {
            case TOP:
                startY = bounds1.getMinY();
                endY = bounds2.getMaxY();
                break;
            case BOTTOM:
                startY = bounds1.getMaxY();
                endY = bounds2.getMinY();
                break;
            case CENTER:
                startY = bounds1.getMinY() + bounds1.getHeight() / 2;
                endY = bounds2.getMinY() + bounds2.getHeight() / 2;
                break;
            default:
                throw new RuntimeException();
        }

        return new Point2D[]{new Point2D(startX, startY), new Point2D(endX, endY)};
    }

    private CalculatorUtils() {
    }
}
