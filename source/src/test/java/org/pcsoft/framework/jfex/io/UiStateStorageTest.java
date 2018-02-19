package org.pcsoft.framework.jfex.io;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class UiStateStorageTest {
    private static final String KEY_INT = "key.int";
    private static final String KEY_STR = "key.str";
    private static final String KEY_BOOL = "key.bool";
    private static final String KEY_DOUBLE = "key.double";
    private static final String KEY_ENUM = "key.enum";

    public enum TestEnum {
        One,
        Two,
        Three
    }

    @BeforeClass
    public static void init() throws IOException {
        final UiStateStorage stateStorage = UiStateStorage.getInstance();
        stateStorage.setStoragePlace(UiStateStorage.StoragePlace.Application);
        stateStorage.setFilename(".test");

        Assert.assertEquals(new File(".test"), stateStorage.getStateFile());

        final Properties properties = new Properties();
        properties.setProperty("default." + KEY_INT, "10");
        properties.setProperty("default." + KEY_STR, "123");
        properties.setProperty("default." + KEY_BOOL, "true");
        properties.setProperty("default." + KEY_ENUM, "Three");
        try (final FileOutputStream out = new FileOutputStream(stateStorage.getStateFile())) {
            properties.store(out, "UI State");
        }
    }

    @AfterClass
    public static void done() throws IOException {
        FileUtils.forceDelete(UiStateStorage.getInstance().getStateFile());
    }

    @Test
    public void test() throws IOException {
        final Properties properties = new Properties();

        final IntegerProperty integerProperty = new SimpleIntegerProperty();
        final StringProperty stringProperty = new SimpleStringProperty();
        final BooleanProperty booleanProperty = new SimpleBooleanProperty();
        final DoubleProperty doubleProperty = new SimpleDoubleProperty();
        final ObjectProperty<TestEnum> enumProperty = new SimpleObjectProperty<>();

        final UiStateStorage stateStorage = UiStateStorage.getInstance();
        stateStorage.getDefaultPropertyGroup().registerProperty(KEY_INT, Number.class, integerProperty, v -> Math.min(100, Math.max(0, v.intValue())));
        stateStorage.getDefaultPropertyGroup().registerProperty(KEY_STR, String.class, stringProperty);
        stateStorage.getDefaultPropertyGroup().registerProperty(KEY_BOOL, Boolean.class, booleanProperty);
        stateStorage.getDefaultPropertyGroup().registerProperty(KEY_DOUBLE, Number.class, doubleProperty);
        stateStorage.getDefaultPropertyGroup().registerProperty(KEY_ENUM, TestEnum.class, enumProperty);

        Assert.assertEquals(10, integerProperty.get());
        Assert.assertEquals("123", stringProperty.get());
        Assert.assertEquals(true, booleanProperty.get());
        Assert.assertEquals(0d, doubleProperty.get(), 0d);
        Assert.assertEquals(TestEnum.Three, enumProperty.get());

        integerProperty.setValue(99);
        stringProperty.setValue("abc");
        booleanProperty.setValue(false);
        doubleProperty.setValue(1.2d);
        enumProperty.setValue(TestEnum.One);

        try (final FileInputStream in = new FileInputStream(stateStorage.getStateFile())) {
            properties.load(in);
            Assert.assertEquals("99.0", properties.get("default." + KEY_INT));
            Assert.assertEquals("abc", properties.get("default." + KEY_STR));
            Assert.assertEquals("false", properties.get("default." + KEY_BOOL));
            Assert.assertEquals("1.2", properties.get("default." + KEY_DOUBLE));
            Assert.assertEquals("One", properties.get("default." + KEY_ENUM));
        }

        integerProperty.setValue(-99);

        try (final FileInputStream in = new FileInputStream(stateStorage.getStateFile())) {
            properties.load(in);
            Assert.assertEquals("0.0", properties.get("default." + KEY_INT));
        }

        integerProperty.setValue(199);

        try (final FileInputStream in = new FileInputStream(stateStorage.getStateFile())) {
            properties.load(in);
            Assert.assertEquals("100.0", properties.get("default." + KEY_INT));
        }
    }

}