package org.pcsoft.framework.jfex.commons.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jfex.commons.property.CastWrapperProperty;


public class CastWrapperPropertyTest {
    private static abstract class A {

    }

    private static final class B extends A {

    }

    private static final class C extends A {

    }

    @Test
    public void test() {
        final ObjectProperty<A> baseA = new SimpleObjectProperty<>();
        final ObjectProperty<B> specificB = new CastWrapperProperty<>(B.class, baseA);
        final ObjectProperty<C> specificC = new CastWrapperProperty<>(C.class, baseA);

        Assert.assertNull(baseA.get());
        Assert.assertNull(specificB.get());
        Assert.assertNull(specificC.get());

        baseA.setValue(new B());

        Assert.assertNotNull(baseA.get());
        Assert.assertNotNull(specificB.get());
        Assert.assertNull(specificC.get());

        baseA.setValue(new C());

        Assert.assertNotNull(baseA.get());
        Assert.assertNull(specificB.get());
        Assert.assertNotNull(specificC.get());

        baseA.setValue(null);

        Assert.assertNull(baseA.get());
        Assert.assertNull(specificB.get());
        Assert.assertNull(specificC.get());

        //********************************************************************************
        //********************************************************************************
        //********************************************************************************

        specificB.setValue(new B());

        Assert.assertNotNull(baseA.get());
        Assert.assertNotNull(specificB.get());
        Assert.assertNull(specificC.get());

        specificC.setValue(new C());

        Assert.assertNotNull(baseA.get());
        Assert.assertNull(specificB.get());
        Assert.assertNotNull(specificC.get());

        specificC.setValue(null);

        Assert.assertNull(baseA.get());
        Assert.assertNull(specificB.get());
        Assert.assertNull(specificC.get());
    }

}