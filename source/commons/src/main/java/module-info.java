module org.pcsoft.jfex.commons {
    requires javafx.base;
    requires commons.lang;
    requires org.apache.commons.codec;
    requires slf4j.api;

    exports org.pcsoft.framework.jfex.commons.converter;
    exports org.pcsoft.framework.jfex.commons.io;
    exports org.pcsoft.framework.jfex.commons.property;
    exports org.pcsoft.framework.jfex.commons.threading;
    exports org.pcsoft.framework.jfex.commons.util;

    exports org.pcsoft.framework.jfex.commons.util.internal to org.pcsoft.jfex.controls, org.pcsoft.jfex.mvvm;
}