package org.pcsoft.framework.jfex.mvvm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ResourceBundle;

public final class Fxml<T extends FxmlView<M>, M extends FxmlViewModel> {
    public static <T extends FxmlView<M>, M extends FxmlViewModel> Fxml<T, M> from(Class<T> viewClass) {
        final ParameterizedType parameterizedType = (ParameterizedType) viewClass.getGenericSuperclass();
        final Class<M> modelClass = findModelClass(parameterizedType);

        return new Fxml<>(viewClass, modelClass);
    }

    private static <M extends FxmlViewModel>Class<M> findModelClass(ParameterizedType parameterizedType) {
        for (final Type type : parameterizedType.getActualTypeArguments()) {
            if (type instanceof Class && FxmlViewModel.class.isAssignableFrom((Class<?>) type))
                return (Class<M>) type;
            else if (type instanceof ParameterizedType && FxmlViewModel.class.isAssignableFrom((Class<?>) ((ParameterizedType) type).getRawType()))
                return (Class<M>)((ParameterizedType) type).getRawType();
        }

        throw new IllegalStateException("Unable to find view model class in generics of " + parameterizedType.getTypeName());
    }

    private final FXMLLoader loader;
    private final Class<T> viewClass;
    private final Class<M> modelClass;

    private final M model;
    private final T controller;

    public Fxml(Class<T> viewClass, Class<M> modelClass) {
        this.viewClass = viewClass;
        this.modelClass = modelClass;

        try {
            this.controller = viewClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Unable to load fxml for " + viewClass.getName(), e);
        }
        try {
            this.model = modelClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Unable to load fxml for " + modelClass.getName(), e);
        }
        this.controller.viewModel = this.model;

        this.loader = new FXMLLoader();
        this.loader.setControllerFactory(param -> controller);
        this.loader.setLocation(viewClass.getResource("/" + viewClass.getName().replace('.', '/') + ".fxml"));
    }

    public Fxml<T, M> withRoot(Object root) {
        loader.setRoot(root);
        return this;
    }

    public Fxml<T, M> withResources(ResourceBundle resourceBundle) {
        loader.setResources(resourceBundle);
        return this;
    }

    public FxmlTuple<T, M> load() {
        try {
            final Parent view = loader.load();
            if (view.getScene() != null) {
                model.onAttached();
            }
            view.sceneProperty().addListener((v, o, n) -> {
                if (n != null) {
                    model.onAttached();
                } else {
                    model.onDetached();
                }
            });

            return new FxmlTuple<>(view, controller, model);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load fxml", e);
        }
    }

    public static final class FxmlTuple<TT extends FxmlView<MM>, MM extends FxmlViewModel> {
        private final Parent view;
        private final TT viewController;
        private final MM viewModel;

        public FxmlTuple(Parent view, TT viewController, MM viewModel) {
            this.view = view;
            this.viewController = viewController;
            this.viewModel = viewModel;
        }

        public Parent getView() {
            return view;
        }

        public TT getViewController() {
            return viewController;
        }

        public MM getViewModel() {
            return viewModel;
        }
    }
}
