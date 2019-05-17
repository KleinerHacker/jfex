package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.scene.control.TableColumn;
import org.pcsoft.framework.jfex.controls.type.TableColumnWrapper;


final class DataTableColumnWrapper<S, T> extends TableColumnWrapper<Item, S, T> {
    public DataTableColumnWrapper() {
    }

    public DataTableColumnWrapper(TableColumn<S, T> wrappedColumn) {
        super(wrappedColumn);
    }

    @Override
    protected S convertIntoWrappedType(Item item) {
        return null;
    }

    @Override
    protected Item extractFromWrappedType(S s) {
        return null;
    }
}
