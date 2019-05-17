package org.pcsoft.framework.jfex.controls.ui.component.workflow.type;

import javafx.beans.property.Property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;


@Retention(RetentionPolicy.RUNTIME)
@Target(value={TYPE})
public @interface WorkflowPropertyEditorInfo {

    Class<? extends Property> propertyType();
    Class innerPropertyType() default Object.class;

}
