package org.pcsoft.framework.jfex.workflow.history;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;

import java.util.concurrent.atomic.AtomicBoolean;


public final class HistoryModel {
    private final ListProperty<Command> commandList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final IntegerProperty historyCommandIndex = new SimpleIntegerProperty(0);

    private final BooleanBinding canRedo, canUndo;
    private final ObjectBinding<Command> lastCommand, nextCommand;

    private final MapProperty<Integer, ChangeListener> propertyBindingMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

    public HistoryModel() {
        lastCommand = commandList.valueAt(historyCommandIndex);
        nextCommand = commandList.valueAt(historyCommandIndex.subtract(1));
        canUndo = lastCommand.isNotNull();
        canRedo = nextCommand.isNotNull();
    }

    public void execute(final Command command) {
        //Cleanup redo list, if needed
        if (historyCommandIndex.get() > 0) {
            //Remove all redo (next) commands before index
            for (int i=0; i<historyCommandIndex.get(); i++) {
                commandList.remove(0);
            }
            //Reset index to 0
            historyCommandIndex.set(0);
        }

        commandList.add(0, command);
        command.execute();
    }

    public boolean undo() {
        if (!canUndo.get())
            return false;

        lastCommand.get().undo();//Undo last command
        historyCommandIndex.set(historyCommandIndex.get() + 1);//Add index to next last command
        return true;
    }

    public boolean redo() {
        if (!canRedo.get())
            return false;

        nextCommand.get().execute();//Redo next command
        historyCommandIndex.set(historyCommandIndex.get() - 1);//Remove index to last next command
        return true;
    }

    public void bindProperty(final Property property) {
        final int identityHashCode = System.identityHashCode(property);
        if (propertyBindingMap.containsKey(identityHashCode))
            return;

        final AtomicBoolean ignore = new AtomicBoolean(false);
        propertyBindingMap.put(identityHashCode, (v, o, n) -> {
            if (ignore.get())
                return;

            execute(new Command() {
                @Override
                public void execute() {
                    ignore.set(true);
                    property.setValue(n);
                    ignore.set(false);
                }

                @Override
                public void undo() {
                    ignore.set(true);
                    property.setValue(o);
                    ignore.set(false);
                }
            });
        });
        property.addListener(propertyBindingMap.get(identityHashCode));
    }

    public void unbindProperty(final Property property) {
        final int identityHashCode = System.identityHashCode(property);
        if (!propertyBindingMap.containsKey(identityHashCode))
            return;

        property.removeListener(propertyBindingMap.get(identityHashCode));
        propertyBindingMap.remove(identityHashCode);
    }

    public Command getLastCommand() {
        return lastCommand.get();
    }

    public ObjectBinding<Command> lastCommandProperty() {
        return lastCommand;
    }

    public Boolean getCanRedo() {
        return canRedo.get();
    }

    public BooleanBinding canRedoProperty() {
        return canRedo;
    }

    public Boolean getCanUndo() {
        return canUndo.get();
    }

    public BooleanBinding canUndoProperty() {
        return canUndo;
    }

    public Command getNextCommand() {
        return nextCommand.get();
    }

    public ObjectBinding<Command> nextCommandProperty() {
        return nextCommand;
    }
}
