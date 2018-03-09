package org.pcsoft.framework.jfex.ui.component.workflow.type;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Descriptor for {@link WorkflowElementInfo}.<br/>
 * <br/>
 * Generate a descriptor from a clazz they need a {@link WorkflowElementInfo}-Annotation.
 */
public final class WorkflowElementInfoDescriptor implements Serializable, Comparable<WorkflowElementInfoDescriptor> {
    private static final String DEFAULT_GROUP = "Other";

    private final String name, description, group;
    private final byte[] smallIcon, bigIcon;
    private final String defaultExpandedPropertyGroup;
    private final Class<? extends WorkflowElement> elementClass;

    public WorkflowElementInfoDescriptor(final Class<? extends WorkflowElement> clazz) {
        final WorkflowElementInfo workflowElementInfo = clazz.getAnnotation(WorkflowElementInfo.class);
        if (workflowElementInfo == null)
            throw new IllegalStateException("Unable to find annotation '" + WorkflowElementInfo.class.getName() + "' on class: " + clazz.getName());

        name = workflowElementInfo.name();
        description = workflowElementInfo.description();
        group = workflowElementInfo.group().isEmpty() ? DEFAULT_GROUP : workflowElementInfo.group();
        try {
            smallIcon = workflowElementInfo.smallIconUrl().isEmpty() ? null : IOUtils.toByteArray(getClass().getResourceAsStream(workflowElementInfo.smallIconUrl()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Small Icon of class '" + clazz.getName() + "' not loadable", e);
        }
        try {
            bigIcon = workflowElementInfo.smallIconUrl().isEmpty() ? null : IOUtils.toByteArray(getClass().getResourceAsStream(workflowElementInfo.bigIconUrl()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Big Icon of class '" + clazz.getName() + "' not loadable", e);
        }
        defaultExpandedPropertyGroup = workflowElementInfo.defaultExpandedPropertyGroup();

        elementClass = clazz;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGroup() {
        return group;
    }

    public byte[] getSmallIcon() {
        return smallIcon;
    }

    public byte[] getBigIcon() {
        return bigIcon;
    }


    public String getDefaultExpandedPropertyGroup() {
        return defaultExpandedPropertyGroup;
    }

    public Class<? extends WorkflowElement> getElementClass() {
        return elementClass;
    }

    @Override
    public int compareTo(WorkflowElementInfoDescriptor o) {
        return getName().compareTo(o.getName());
    }
}
