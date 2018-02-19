package org.pcsoft.framework.jfex.component;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.pcsoft.framework.jfex.data.ListViewEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SelectionMoveDownButton extends Button {
    private final ObjectProperty<Node> forList = new SimpleObjectProperty<>();
    private final BooleanProperty allowMultipleSelectionMoving = new SimpleBooleanProperty(false);
    private final IntegerProperty shiftCount = new SimpleIntegerProperty(1);

    private MultipleSelectionModel<?> selectionModel = null;
    private ListProperty<?> items = null;

    public SelectionMoveDownButton() {
        super(null, new ImageView(new Image(SelectionMoveDownButton.class.getResourceAsStream("/icons/ic_down16.png"))));
        forList.addListener((v, o, n) -> {
            selectionModel = null;
            disableProperty().unbind();
            items = null;

            if (n == null)
                return;

            if (n instanceof ListView) {
                selectionModel = ((ListView) n).getSelectionModel();
                items = new SimpleListProperty<>(((ListView) n).getItems());
            } else if (n instanceof ListViewEx) {
                if (((ListViewEx) n).getHeaderKeyExtractor() != null || ((ListViewEx) n).getValueComparator() != null)
                    throw new IllegalStateException("Move Down Selection only usable with unsorted and not grouped lists");
                selectionModel = ((ListViewEx) n).getSelectionModel();
                items = new SimpleListProperty<>(((ListViewEx) n).getItems());
            } else if (n instanceof TableView) {
                selectionModel = ((TableView) n).getSelectionModel();
                items = new SimpleListProperty<>(((TableView) n).getItems());
            } else
                throw new IllegalArgumentException(String.format("Only list or table controls are allowed. Found: %s", n.getClass().getName()));

            updateDisabledStateBinding(n);
        });
    }

    protected void updateDisabledStateBinding(Node n) {
        final SimpleListProperty<Integer> selectedIndicesProperty = new SimpleListProperty<>(selectionModel.getSelectedIndices());

        final BooleanBinding multiSelection = Bindings.createBooleanBinding(() -> selectionModel.getSelectedIndices().size() > 1, selectionModel.selectedItemProperty());

        final BooleanBinding multiSelectionDisabledState = multiSelection.and(allowMultipleSelectionMoving.not());
        final BooleanBinding noSelectionDisabledState = selectionModel.selectedItemProperty().isNull();
        final BooleanBinding onBottomDisabledState = Bindings.integerValueAt(selectedIndicesProperty, selectedIndicesProperty.sizeProperty().subtract(1)).greaterThanOrEqualTo(items.sizeProperty().subtract(shiftCount));
        final BooleanBinding filterDisabledState;
        if (n instanceof ListViewEx) {
            final ListViewEx tmp = (ListViewEx) n;
            filterDisabledState = Bindings.createBooleanBinding(() -> tmp.getFilteredItems().size() != tmp.getItems().size(), tmp.getItems(), tmp.getFilteredItems());
        } else {
            filterDisabledState = Bindings.createBooleanBinding(() -> false);
        }

        disableProperty().bind(noSelectionDisabledState.or(multiSelectionDisabledState).or(onBottomDisabledState).or(filterDisabledState));
    }

    public SelectionMoveDownButton(final Node forList) {
        this();
        this.forList.set(forList);
    }

    @Override
    public void fire() {
        super.fire();

        if (selectionModel != null && items != null) {
            for (final int index : new ArrayList<>(selectionModel.getSelectedIndices())) {
                for (int i=0; i<shiftCount.get(); i++) {
                    final Object elementTop = items.get(index + i);
                    final Object elementBottom = items.get(index + (i + 1));
                    items.removeAll(Arrays.asList(elementTop, elementBottom));
                    items.addAll(index + i, (List) Arrays.asList(elementBottom, elementTop));
                }
            }
        }
    }

    public int getShiftCount() {
        return shiftCount.get();
    }

    public IntegerProperty shiftCountProperty() {
        return shiftCount;
    }

    public void setShiftCount(int shiftCount) {
        this.shiftCount.set(shiftCount);
    }

    public boolean isAllowMultipleSelectionMoving() {
        return allowMultipleSelectionMoving.get();
    }

    public BooleanProperty allowMultipleSelectionMovingProperty() {
        return allowMultipleSelectionMoving;
    }

    public void setAllowMultipleSelectionMoving(boolean allowMultipleSelectionMoving) {
        this.allowMultipleSelectionMoving.set(allowMultipleSelectionMoving);
    }

    public Node getForList() {
        return forList.get();
    }

    public ObjectProperty<Node> forListProperty() {
        return forList;
    }

    public void setForList(Node forList) {
        this.forList.set(forList);
    }
}
