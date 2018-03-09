package org.pcsoft.framework.jfex.ui.component.workflow.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;


@Retention(RetentionPolicy.RUNTIME)
@Target(value={TYPE})
public @interface WorkflowElementInfo {

    String name();
    String description();
    String group() default "";

    String smallIconUrl() default "";
    String bigIconUrl() default "";

    /**
     * Default expanded property group. If this attribute is not set the first property group is expanded.
     * @return
     */
    String defaultExpandedPropertyGroup() default "";

}
