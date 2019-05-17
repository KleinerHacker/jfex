package org.pcsoft.framework.jfex.controls.ui.component.data;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


public class ListItemClickEvent<T> extends Event {
    private final T item;

    public ListItemClickEvent(@NamedArg("eventType") EventType<? extends Event> eventType, T item) {
        super(eventType);
        this.item = item;
    }

    public ListItemClickEvent(@NamedArg("source") Object source, @NamedArg("target") EventTarget target, @NamedArg("eventType") EventType<? extends Event> eventType, T item) {
        super(source, target, eventType);
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
