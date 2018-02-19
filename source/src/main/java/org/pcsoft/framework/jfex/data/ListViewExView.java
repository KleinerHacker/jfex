package org.pcsoft.framework.jfex.data;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.pcsoft.framework.jfex.component.OverlayProgressIndicatorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ListViewExView<T, G> extends MultipleSelectionDataView<T, G, ListCell, ListViewExViewModel<T, G>> {

    @FXML
    private BorderPane pnlList;
    @FXML
    private ToolBar toolbar;
    @FXML
    private Label lblEmpty;
    @FXML
    private ListView<Item> lst;
    @FXML
    private OverlayProgressIndicatorPane pnlProgress;

    private MultipleSelectionModel<Item> originalSelectionModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.originalSelectionModel = lst.getSelectionModel();

        lst.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                renderCell(this, item, empty);
            }

            @Override
            public void updateSelected(boolean selected) {
                super.updateSelected(selected);
                renderCell(this, getItem(), isEmpty());
            }
        });
        lblEmpty.textProperty().bind(viewModel.emptyTextProperty());
        lblEmpty.styleProperty().bind(viewModel.emptyTextStyleProperty());
        lblEmpty.visibleProperty().bind(new SimpleListProperty<>(lst.getItems()).emptyProperty());

        toolbar.visibleProperty().bind(viewModel.showToolbarProperty());
        Bindings.bindContent(toolbar.getItems(), viewModel.getToolbarItems());
        viewModel.orientationProperty().addListener((v, o, n) -> {
            if (o != null) {
                setComponent(o, true);
            }

            if (n != null) {
                setComponent(n, false);
            }
        });
    }

    private void setComponent(final ListViewEx.ToolbarOrientation orientation, final boolean clear) {
        final Node node;
        if (clear) {
            node = null;
        }
        else {
            node = toolbar;
            toolbar.setOrientation(orientation.getOrientation());
        }
        switch (orientation) {
            case LEFT:
                pnlList.setLeft(node);
                break;
            case RIGHT:
                pnlList.setRight(node);
                break;
            case TOP:
                pnlList.setTop(node);
                break;
            case BOTTOM:
                pnlList.setBottom(node);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    protected MultipleSelectionModel<Item> getInnerSelectionModel() {
        return lst.getSelectionModel();
    }

    @Override
    protected List<Item> getComponentList() {
        return lst.getItems();
    }

    @Override
    protected void onShowProgress(String initialText) {
        pnlProgress.fadeIn(initialText);
    }

    @Override
    protected void onHideProgress() {
        pnlProgress.fadeOut();
    }

    @Override
    protected Item getSelection() {
        return lst.getSelectionModel().getSelectedItem();
    }

    @Override
    protected void setSelection(Item item) {
        lst.getSelectionModel().select(item);
    }

    @FXML
    private void onListMouseClick(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2 && lst.getSelectionModel().getSelectedItem() != null) {
            if (viewModel.getOnItemClick() != null) {
                viewModel.getOnItemClick().handle(new ListItemClickEvent<T>(this, new EventTarget() {
                    @Override
                    public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
                        return this.buildEventDispatchChain(tail);
                    }
                }, MouseEvent.MOUSE_CLICKED, (T) lst.getSelectionModel().getSelectedItem().getData()));
            }
        }
    }
}
