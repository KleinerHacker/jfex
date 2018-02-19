package org.pcsoft.framework.jfex.workflow.utils;


public final class MouseDragging {
    private final double initialX, initialY;

    public MouseDragging(double initialX, double initialY) {
        this.initialX = initialX;
        this.initialY = initialY;
    }

    public double getInitialX() {
        return initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public double calculateX(double currentX) {
        return initialX + currentX;
    }

    public double calculateY(double currentY) {
        return initialY + currentY;
    }
}
