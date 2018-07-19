package org.pcsoft.framework.jfex.ui.component.data;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.IndexedCell;
import org.pcsoft.framework.jfex.threading.JfxUiThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public abstract class SimpleDataView<T, G, C extends IndexedCell, M extends SimpleDataViewModel<T, G, C>> implements FxmlView<M>, Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDataView.class);

    @InjectViewModel
    protected M viewModel;

    private final AtomicBoolean ignoreUpdate = new AtomicBoolean(false);

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.itemsProperty().addListener((ListChangeListener<T>) c -> refresh(false));
        viewModel.itemsProperty().addListener((InvalidationListener) o -> refresh(false));
        viewModel.headerKeyExtractorProperty().addListener(o -> refresh(false));
        viewModel.headerComparatorProperty().addListener(o -> refresh(false));
        viewModel.valueComparatorProperty().addListener(o -> refresh(false));
        viewModel.itemLoaderProperty().addListener(o -> refresh(true));
        viewModel.filterCallbackProperty().addListener(o -> refresh(false));
        viewModel.filterValueProperty().addListener(o -> refresh(false));

        this.resourceBundle = resources;
    }

    void refresh(boolean needsContentUpdate) {
        if (ignoreUpdate.get())
            return;

        if (needsContentUpdate && viewModel.getItemLoader() != null) {
            if (viewModel.getProgressListener() != null) {
                viewModel.getProgressListener().onStartProgress();
            }
            onShowProgress(viewModel.getLoadingText());

            ignoreUpdate.set(true);
            JfxUiThreadPool.submit(() -> {
                LOGGER.debug("Startup async item loading...");
                try {
                    final List<T> itemList = viewModel.getItemLoader().onLoadItems();
                    Platform.runLater(() -> viewModel.getItems().setAll(itemList));
                    final List<T> filteredList;
                    if (viewModel.getFilterCallback() != null) {
                        filteredList = itemList.stream()
                                .filter(item -> viewModel.getFilterCallback().apply(item, viewModel.getFilterValue()))
                                .collect(Collectors.toList());
                    } else {
                        filteredList = itemList == null ? new ArrayList<>() : new ArrayList<>(itemList);
                    }
                    Platform.runLater(() -> {
                        viewModel.getFilteredItems().setAll(filteredList);
                        refreshList();

                        if (viewModel.getProgressListener() != null) {
                            viewModel.getProgressListener().onSuccess();
                        }
                    });
                } catch (Exception e) {
                    LOGGER.error("unable to load list", e);
                    Platform.runLater(() -> {
                        new Alert(Alert.AlertType.ERROR, resourceBundle.getString("cmp.data.error"), ButtonType.OK).showAndWait();
                        if (viewModel.getProgressListener() != null) {
                            viewModel.getProgressListener().onFailure(e);
                        }
                    });
                } finally {
                    Platform.runLater(() -> {
                        onHideProgress();
                        ignoreUpdate.set(false);

                        if (viewModel.getProgressListener() != null) {
                            viewModel.getProgressListener().onFinishProgress();
                        }
                    });
                }
            });
        } else {
            ignoreUpdate.set(true);

            if (viewModel.getFilterCallback() != null) {
                viewModel.getFilteredItems().setAll(
                        viewModel.getItems().stream()
                                .filter(item -> viewModel.getFilterCallback().apply(item, viewModel.getFilterValue()))
                                .collect(Collectors.toList())
                );
            } else {
                viewModel.getFilteredItems().setAll(viewModel.getItems());
            }

            try {
                refreshList();
            } finally {
                ignoreUpdate.set(false);
            }
        }
    }

    private void refreshList() {
        onStartRefresh();
        if (viewModel.getOnItemsLoading() != null) {
            viewModel.getOnItemsLoading().run();
        }

        final Item selection = getSelection();
        setSelection(null);

        final List<Item> componentList = getComponentList();

        componentList.clear();
        if (viewModel.getHeaderKeyExtractor() != null) {
            final List<G> groupItemList = new ArrayList<>();

            groupItemList.addAll(viewModel.getFilteredItems().stream()
                    .map(item -> viewModel.getHeaderKeyExtractor().apply(item))
                    .distinct()
                    .collect(Collectors.toList())
            );
            if (viewModel.getHeaderComparator() != null) {
                Collections.sort(groupItemList, viewModel.getHeaderComparator());
            }

            for (final G groupItem : groupItemList) {
                componentList.add(new ItemGroup<>(groupItem));

                final List<T> valueItemList = viewModel.getFilteredItems().stream()
                        .filter(item -> Objects.equals(viewModel.getHeaderKeyExtractor().apply(item), groupItem))
                        .collect(Collectors.toList());
                refreshValueList(valueItemList);
            }
        } else {
            refreshValueList(viewModel.getFilteredItems());
        }

        setSelection(
                componentList.stream()
                .filter(item -> item.equals(selection))
                .findFirst().orElse(selection)
        );
        onFinishRefresh();
        if (viewModel.getOnItemsLoaded() != null) {
            viewModel.getOnItemsLoaded().run();
        }
    }

    private void refreshValueList(final List<T> valueItemList) {
        if (viewModel.getValueComparator() != null) {
            Collections.sort(valueItemList, viewModel.getValueComparator());
        }

        getComponentList().addAll(
                valueItemList.stream()
                        .map(ItemValue::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Is called if refreshing of showing list content is started. So you can handle e. g. safe selection or other things
     */
    protected void onStartRefresh() {
        //Empty, only for overwriting
    }

    /**
     * Is called if refreshing of showing list content is finished. So you can handle e. g. reload selection or other things
     */
    protected void onFinishRefresh() {
        //Empty, only for overwriting
    }

    /**
     * Returns the list of the component within a list of items
     * @return
     */
    protected abstract List<Item> getComponentList();

    /**
     * Call it to do cell rendering action with group and value items
     * @param cell
     * @param item
     * @param empty
     */
    protected final void renderCell(C cell, Item item, boolean empty) {
        cell.setText(null);
        cell.setGraphic(null);
        cell.setDisable(true);

        if (item instanceof ItemGroup) {
            if (viewModel.getHeaderCellRendererCallback() != null) {
                viewModel.getHeaderCellRendererCallback().onRender(cell, (G) item.getData(), empty);
            } else {
                cell.setText(item.getData().toString());
            }
        } else if (item instanceof ItemValue) {
            if (viewModel.getValueCellRendererCallback() != null) {
                viewModel.getValueCellRendererCallback().onRender(cell, (T) item.getData(), empty);
                cell.setDisable(false);
            } else {
                cell.setText(item.getData().toString());
            }
        } else if (item == null) {
            if (viewModel.getEmptyCellRendererCallback() != null) {
                viewModel.getEmptyCellRendererCallback().onRender(cell, null, empty);
            }
        }
    }

    protected abstract void onShowProgress(final String initialText);
    protected abstract void onHideProgress();

    protected abstract Item getSelection();
    protected abstract void setSelection(final Item item);

    public Runnable getOnItemsLoaded() {
        return viewModel.getOnItemsLoaded();
    }

    public ObjectProperty<Runnable> onItemsLoadedProperty() {
        return viewModel.onItemsLoadedProperty();
    }

    public void setOnItemsLoaded(Runnable onItemsLoaded) {
        viewModel.setOnItemsLoaded(onItemsLoaded);
    }

    public Runnable getOnItemsLoading() {
        return viewModel.getOnItemsLoading();
    }

    public ObjectProperty<Runnable> onItemsLoadingProperty() {
        return viewModel.onItemsLoadingProperty();
    }

    public void setOnItemsLoading(Runnable onItemsLoading) {
        viewModel.setOnItemsLoading(onItemsLoading);
    }
}
