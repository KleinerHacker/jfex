package org.pcsoft.framework.jfex.component;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Tooltip;


public class DetailTooltip extends Tooltip {
    private final TooltipDetailPane pnl = new TooltipDetailPane();

    public DetailTooltip(final String title, final String description) {
        this();
        setTitle(title);
        setDescription(description);
    }

    public DetailTooltip() {
        setGraphic(pnl);
    }

    public String getTitle() {
        return pnl.getTitle();
    }

    public StringProperty titleProperty() {
        return pnl.titleProperty();
    }

    public void setTitle(String title) {
        pnl.setTitle(title);
    }

    public String getDescription() {
        return pnl.getDescription();
    }

    public StringProperty descriptionProperty() {
        return pnl.descriptionProperty();
    }

    public void setDescription(String description) {
        pnl.setDescription(description);
    }
}
