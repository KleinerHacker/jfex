package org.pcsoft.framework.jfex.commons.io;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represent a class to load and store UI states
 */
public final class UiStateStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(UiStateStorage.class);

    private static final String GRP_DEFAULT = "%%DEFAULT%%";
    private static final String GRP_SYSTEM = "%%SYSTEM%%";
    private static final String FILE = ".ui";
    private static final String PLACE_APP = null;
    private static final String PLACE_USER = SystemUtils.USER_HOME;
    private static final String PLACE_SYSTEM;

    static {
        if (SystemUtils.IS_OS_WINDOWS) {
            PLACE_SYSTEM = "C:" + SystemUtils.FILE_SEPARATOR + "ProgramData";
        } else if (SystemUtils.IS_OS_UNIX) {
            PLACE_SYSTEM = SystemUtils.FILE_SEPARATOR + "var";
        } else {
            PLACE_SYSTEM = PLACE_APP;
        }
    }

    /**
     * Defines the place of storage of UI state files
     */
    public enum StoragePlace {
        /**
         * Store file in application folder (working directory)
         */
        Application,
        /**
         * Store file in user home directory
         */
        UserHome,
        /**
         * Store file in system (global program data)
         */
        System
    }

    private final class PropertyHolder<T> {
        private final Property<T> property;
        private final Class<T> propertyClass;
        private final Function<T, T> correctingValue;

        private PropertyHolder(Class<T> propertyClass, Property<T> property, Function<T, T> correctingValue) {
            this.property = property;
            this.propertyClass = propertyClass;
            this.correctingValue = correctingValue;
        }
    }

    /**
     * Represent a property group
     */
    public final class PropertyGroup {
        private final String key;
        private final Map<String, PropertyHolder> propertyMap = new HashMap<>();

        private PropertyGroup(String key) {
            this.key = key;
        }

        public String getKey() {
            switch (key) {
                case GRP_DEFAULT:
                    return "default";
                case GRP_SYSTEM:
                    return "system";
                default:
                    return key;
            }
        }

        /**
         * Register a new property under the given key. The key is used as property key for the given value.
         *
         * @param key
         * @param property
         * @param <T>
         */
        public <T> void registerProperty(final String key, final Class<T> propertyClass, final Property<T> property) {
            registerProperty(key, propertyClass, property, v -> v);
        }

        /**
         * Register a new property under the given key. The key is used as property key for the given value.
         *
         * @param key
         * @param property
         * @param correctingValue Function to correct an invalid value before it is set to component or stored
         * @param <T>
         */
        public <T> void registerProperty(final String key, final Class<T> propertyClass, final Property<T> property, final Function<T, T> correctingValue) {
            if (propertyMap.containsKey(key)) {
                unregisterProperty(key);
            }

            LOGGER.debug("Register Property {} in group {}", key, this.getKey());

            property.addListener(storeListener);
            propertyMap.put(key, new PropertyHolder<>(propertyClass, property, correctingValue));

            load();
        }

        /**
         * Register a new property with getter and setter implementation under the given key. The key is used as property key for the given value.
         *
         * @param key
         * @param getter
         * @param setter
         * @param observable
         * @param <T>
         */
        public <T> void registerProperty(final String key, final Class<T> propertyClass, final Supplier<T> getter, final Consumer<T> setter,
                                         final Observable observable) {
            registerProperty(key, propertyClass, getter, setter, observable, null);
        }

        /**
         * Register a new property with getter and setter implementation under the given key. The key is used as property key for the given value.
         *
         * @param key
         * @param getter
         * @param setter
         * @param observable
         * @param defaultValue Default Value in case of NULL
         * @param <T>
         */
        public <T> void registerProperty(final String key, final Class<T> propertyClass, final Supplier<T> getter, final Consumer<T> setter,
                                         final Observable observable, final T defaultValue) {
            registerProperty(key, propertyClass, getter, setter, observable, defaultValue, v -> v);
        }

        /**
         * Register a new property with getter and setter implementation under the given key. The key is used as property key for the given value.
         *
         * @param key
         * @param getter
         * @param setter
         * @param observable
         * @param defaultValue Default Value in case of NULL
         * @param correctingValue Function to correct an invalid value before it is set to component or stored
         * @param <T>
         */
        public <T> void registerProperty(final String key, final Class<T> propertyClass, final Supplier<T> getter, final Consumer<T> setter,
                                         final Observable observable, final T defaultValue, final Function<T, T> correctingValue) {
            registerProperty(key, propertyClass, new SimpleObjectProperty<T>() {
                {
                    if (observable != null) {
                        observable.addListener(o -> fireValueChangedEvent());
                    }
                }

                @Override
                public T get() {
                    return getValue();
                }

                @Override
                public void set(T newValue) {
                    setValue(newValue);
                }

                @Override
                public void setValue(T v) {
                    setter.accept(v == null ? defaultValue : v);
                }

                @Override
                public T getValue() {
                    final T value = getter.get();
                    return value == null ? defaultValue : value;
                }
            }, correctingValue);
        }

        /**
         * Unregister a property from storage
         *
         * @param key
         */
        public void unregisterProperty(final String key) {
            if (!propertyMap.containsKey(key))
                return;

            LOGGER.debug("Unregister Property {} in group {}", key, this.getKey());

            final Property property = propertyMap.get(key).property;
            property.removeListener(storeListener);
            property.setValue(null);
            propertyMap.remove(key);
        }

        public void cleanup() {
            new ArrayList<>(propertyMap.keySet())
                    .forEach(this::unregisterProperty);
        }
    }

    private static UiStateStorage instance = new UiStateStorage();

    public static UiStateStorage getInstance() {
        return instance;
    }

    private final Map<String, PropertyGroup> propertyGroupMap = new HashMap<>();
    private final InvalidationListener storeListener = o -> store();

    private final ObjectProperty<StoragePlace> storagePlace = new SimpleObjectProperty<>();
    private final StringProperty filename = new SimpleStringProperty();

    private final ObjectBinding<File> stateFile;

    private UiStateStorage() {
        stateFile = Bindings.createObjectBinding(() -> {
            final String path;
            if (storagePlace.get() != null) {
                switch (storagePlace.get()) {
                    case Application:
                        path = PLACE_APP;
                        break;
                    case System:
                        path = PLACE_SYSTEM;
                        break;
                    case UserHome:
                        path = PLACE_USER;
                        break;
                    default:
                        throw new RuntimeException();
                }
            } else {
                path = PLACE_APP;
            }

            return new File(path, StringUtils.defaultIfEmpty(filename.get(), FILE));
        }, storagePlace, filename);
    }

    /**
     * Returns the default property group.
     *
     * @return
     */
    public PropertyGroup getDefaultPropertyGroup() {
        return getPropertyGroup(GRP_DEFAULT);
    }

    /**
     * Returns the system property group. <b>Only for internal use!</b>
     *
     * @return
     */
    public PropertyGroup getSystemPropertyGroup() {
        return getPropertyGroup(GRP_SYSTEM);
    }

    /**
     * Returns a property group with given key. If it does not exists the group will be created.
     *
     * @param key
     * @return
     */
    public PropertyGroup getPropertyGroup(final String key) {
        if (!propertyGroupMap.containsKey(key)) {
            LOGGER.debug("Create Property Group {}", key);
            propertyGroupMap.put(key, new PropertyGroup(key));
        }

        return propertyGroupMap.get(key);
    }

    /**
     * Remove and unregister all properties of property group with given key. If group not exists nothing will happen.
     *
     * @param key
     */
    public void removePropertyGroup(final String key) {
        if (!propertyGroupMap.containsKey(key))
            return;

        LOGGER.debug("Remove Property Group {}", key);

        final PropertyGroup propertyGroup = propertyGroupMap.get(key);
        propertyGroup.cleanup();

        propertyGroupMap.remove(key);
    }

    /**
     * Load the given file content to registered properties, see {@link #stateFileProperty()}. Is called automatically if an property was registered.
     */
    public void load() {
        LOGGER.debug("Load UI State File: {}", getStateFile().getAbsolutePath());

        final Properties properties = new Properties();
        try (final FileInputStream in = new FileInputStream(getStateFile())) {
            properties.load(in);

            for (final PropertyGroup propertyGroup : propertyGroupMap.values()) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("> Load Group: {}", propertyGroup.getKey());
                }
                for (final Map.Entry<String, PropertyHolder> entry : propertyGroup.propertyMap.entrySet()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace(">>> Load Property: {}", entry.getKey());
                    }
                    final String value = properties.getProperty(propertyGroup.getKey() + "." + entry.getKey());
                    if (value == null) {
                        LOGGER.warn("unable to find value for key {}.{}", propertyGroup.getKey(), entry.getKey());
                        continue;
                    }

                    final Function correctingValue = entry.getValue().correctingValue;
                    final Class propertyClass = entry.getValue().propertyClass;
                    if (propertyClass == long.class || propertyClass == Long.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Long.parseLong(value)));
                    } else if (propertyClass == int.class || propertyClass == Integer.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Integer.parseInt(value)));
                    } else if (propertyClass == short.class || propertyClass == Short.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Short.parseShort(value)));
                    } else if (propertyClass == byte.class || propertyClass == Byte.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Byte.parseByte(value)));
                    } else if (propertyClass == double.class || propertyClass == Double.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Double.parseDouble(value)));
                    } else if (propertyClass == float.class || propertyClass == Float.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Float.parseFloat(value)));
                    } else if (propertyClass == boolean.class || propertyClass == Boolean.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Boolean.parseBoolean(value)));
                    } else if (propertyClass == char.class || propertyClass == Character.class) {
                        entry.getValue().property.setValue(correctingValue.apply(value.charAt(0)));
                    } else if (propertyClass == String.class || propertyClass == CharSequence.class) {
                        entry.getValue().property.setValue(correctingValue.apply(value));
                    } else if (propertyClass == BigInteger.class) {
                        entry.getValue().property.setValue(correctingValue.apply(new BigInteger(value)));
                    } else if (propertyClass == BigDecimal.class) {
                        entry.getValue().property.setValue(correctingValue.apply(new BigDecimal(value)));
                    } else if (propertyClass == Number.class) {
                        entry.getValue().property.setValue(correctingValue.apply(Double.parseDouble(value)));
                    } else if (propertyClass.isEnum()) {
                        entry.getValue().property.setValue(Enum.valueOf(propertyClass, value));
                    } else
                        throw new IllegalStateException("unable to set a not native type: " + propertyClass.getName());

                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace(">>>>> Value: {}", entry.getValue().property.getValue());
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.warn("Unable to read ui state file '" + getStateFile().getAbsolutePath() + "': " + e.getMessage());
        }
    }

    /**
     * Store the current ui state back into state file. It will be called automatically if an property has changed.
     */
    public void store() {
        LOGGER.debug("Store UI State File: {}", getStateFile().getAbsolutePath());

        final Properties properties = new Properties();
        try (final FileInputStream in = new FileInputStream(getStateFile())) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.warn("unable to reload file '" + getStateFile().getAbsolutePath() + "' before storing data: " + e.getMessage());
            LOGGER.warn("> data can be lost");
        }

        for (final PropertyGroup propertyGroup : propertyGroupMap.values()) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("> Store Group: {}", propertyGroup.key);
            }
            for (final Map.Entry<String, PropertyHolder> entry : propertyGroup.propertyMap.entrySet()) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace(">>> Store Property: {}", entry.getKey());
                }
                final Object value = entry.getValue().correctingValue.apply(entry.getValue().property.getValue());
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace(">>>>> Value: {}", value);
                }
                if (value == null) {
                    LOGGER.warn("unable to write a null value for key {}.{}", propertyGroup.getKey(), entry.getKey());
                    continue;
                }
                if (entry.getValue().propertyClass == Number.class) {
                    properties.setProperty(propertyGroup.getKey() + "." + entry.getKey(),
                            String.valueOf(((Number) value).doubleValue()));
                } else if (entry.getValue().propertyClass.isEnum()) {
                    properties.setProperty(propertyGroup.getKey() + "." + entry.getKey(),
                            ((Enum) value).name());
                } else {
                    properties.setProperty(propertyGroup.getKey() + "." + entry.getKey(),
                            value.toString());
                }
            }
        }

        try (final FileOutputStream out = new FileOutputStream(getStateFile())) {
            properties.store(out, "UI States");
        } catch (IOException e) {
            LOGGER.warn("unable to write ui state file '" + getStateFile().getAbsolutePath() + "': " + e.getMessage());
        }
    }

    public StoragePlace getStoragePlace() {
        return storagePlace.get();
    }

    /**
     * Defines the place to store ui state file. Default is working directory {@link StoragePlace#Application}
     *
     * @return
     */
    public ObjectProperty<StoragePlace> storagePlaceProperty() {
        return storagePlace;
    }

    public void setStoragePlace(StoragePlace storagePlace) {
        this.storagePlace.set(storagePlace);
    }

    public String getFilename() {
        return filename.get();
    }

    /**
     * Define the name of the ui state file, default is '.ui'
     *
     * @return
     */
    public StringProperty filenameProperty() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename.set(filename);
    }

    public File getStateFile() {
        return stateFile.get();
    }

    /**
     * Read Only property for the state file as {@link File} object.
     *
     * @return
     */
    public ObjectBinding<File> stateFileProperty() {
        return stateFile;
    }
}
