open module org.pcsoft.jfex.controls {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.pcsoft.jfex.mvvm;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.pcsoft.jfex.commons;
    requires commons.lang;
    requires pdfbox;
    requires slf4j.api;
    requires commons.io;
    requires javafx.swing;

    exports org.pcsoft.framework.jfex.controls.listener;
    exports org.pcsoft.framework.jfex.controls.type;
    exports org.pcsoft.framework.jfex.controls.util;

    exports org.pcsoft.framework.jfex.controls.ui.component;

    exports org.pcsoft.framework.jfex.controls.ui.component.cell;
    exports org.pcsoft.framework.jfex.controls.ui.component.cell.list;
    exports org.pcsoft.framework.jfex.controls.ui.component.cell.table;
    exports org.pcsoft.framework.jfex.controls.ui.component.cell.tree;
    exports org.pcsoft.framework.jfex.controls.ui.component.cell.tree_table;

    exports org.pcsoft.framework.jfex.controls.ui.component.data;
    exports org.pcsoft.framework.jfex.controls.ui.component.paint;
    exports org.pcsoft.framework.jfex.controls.ui.component.toolbox;

    exports org.pcsoft.framework.jfex.controls.ui.component.workflow;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.component;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.component.cell;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.editor;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.element;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.history;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.listener;
    exports org.pcsoft.framework.jfex.controls.ui.component.workflow.type;

    exports org.pcsoft.framework.jfex.controls.ui.dialog;
    exports org.pcsoft.framework.jfex.controls.ui.splash;
    exports org.pcsoft.framework.jfex.controls.ui.wizard;
}