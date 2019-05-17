package org.pcsoft.framework.jfex.controls.ui.component.data;

import java.util.List;


@FunctionalInterface
public interface ItemLoader<T> {

    List<T> onLoadItems() throws Exception;

}
