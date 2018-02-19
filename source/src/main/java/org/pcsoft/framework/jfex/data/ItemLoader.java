package org.pcsoft.framework.jfex.data;

import java.util.List;


@FunctionalInterface
public interface ItemLoader<T> {

    List<T> onLoadItems() throws Exception;

}
