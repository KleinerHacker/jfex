package org.pcsoft.framework.jfex.controls.type;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class to bundle multiple resource bundles to one.
 */
public class MultipleResourceBundle extends ResourceBundle {
    private final List<ResourceBundle> resourceBundleList;

    /**
     * Create an instance
     * @param resourceBundleList list of resource bundles to merge
     */
    public MultipleResourceBundle(ResourceBundle... resourceBundleList) {
        this.resourceBundleList = Arrays.asList(resourceBundleList);
    }

    /**
     * Create an instance
     * @param resourceBundleList list of resource bundles to merge
     */
    public MultipleResourceBundle(List<ResourceBundle> resourceBundleList) {
        this.resourceBundleList = resourceBundleList;
    }

    @Override
    protected Object handleGetObject(String key) {
        for (final ResourceBundle resourceBundle : resourceBundleList) {
            if (resourceBundle.containsKey(key))
                return resourceBundle.getObject(key);
        }

        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        final List<String> keyList = new ArrayList<>();
        for (final ResourceBundle resourceBundle : resourceBundleList) {
            final Enumeration<String> keys = resourceBundle.getKeys();
            while (keys.hasMoreElements()) {
                keyList.add(keys.nextElement());
            }
        }

        return new Enumeration<>() {
            private final AtomicInteger counter = new AtomicInteger();

            @Override
            public boolean hasMoreElements() {
                return counter.get() < keyList.size();
            }

            @Override
            public String nextElement() {
                return keyList.get(counter.getAndIncrement());
            }
        };
    }
}
