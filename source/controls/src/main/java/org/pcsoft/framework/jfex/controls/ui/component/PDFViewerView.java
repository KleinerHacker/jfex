package org.pcsoft.framework.jfex.controls.ui.component;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.pcsoft.framework.jfex.commons.converter.FloatPercentageConverter;
import org.pcsoft.framework.jfex.commons.threading.JfxUiThreadPool;
import org.pcsoft.framework.jfex.controls.util.FXChooserUtils;
import org.pcsoft.framework.jfex.mvvm.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class PDFViewerView extends FxmlView<PDFViewerViewModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFViewerView.class);
    private static final float ZOOM_FACTOR = 0.1f;

    @FXML
    private ScrollPane pnlScrollPane;

    @FXML
    private VBox pnlSinglePage;
    @FXML
    private VBox pnlQueuedPage;
    @FXML
    private FlowPane pnlFlowedPage;

    @FXML
    private HBox pnlPageControl;
    @FXML
    private HBox pnlContentControl;
    @FXML
    private HBox pnlViewControl;
    @FXML
    private HBox pnlPageViewControl;

    @FXML
    private Button btnPagePrevious;
    @FXML
    private Label lblPageCurrent;
    @FXML
    private Label lblPageCount;
    @FXML
    private Button btnPageNext;

    @FXML
    private ToolBar toolbar;

    @FXML
    private ImageView imgSinglePagePDF;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnOpen;
    @FXML
    private ComboBox<Float> cmbZoom;
    @FXML
    private ToggleButton btnSinglePage;
    @FXML
    private ToggleButton btnQueuedPage;
    @FXML
    private ToggleButton btnFlowedPage;

    private PDFRenderer pdfRenderer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolbar.visibleProperty().bind(viewModel.showToolbarProperty());
        btnSave.disableProperty().bind(viewModel.documentOpenedProperty().not());
        pnlViewControl.disableProperty().bind(viewModel.documentOpenedProperty().not());
        pnlPageControl.visibleProperty().bind(viewModel.singlePageActiveProperty());
        pnlContentControl.visibleProperty().bind(viewModel.showContentControlProperty());
        btnOpen.visibleProperty().bind(viewModel.showOpenContentProperty());
        pnlViewControl.visibleProperty().bind(viewModel.showViewControlProperty());
        pnlPageViewControl.visibleProperty().bind(viewModel.showPageViewControlProperty());
        pnlSinglePage.visibleProperty().bind(viewModel.singlePageActiveProperty());
        pnlQueuedPage.visibleProperty().bind(viewModel.queuedPageActiveProperty());
        pnlFlowedPage.visibleProperty().bind(viewModel.flowedPageActiveProperty());
        pnlScrollPane.fitToWidthProperty().bind(viewModel.flowedPageActiveProperty());

        btnSinglePage.selectedProperty().bindBidirectional(viewModel.singlePageActiveProperty());
        btnQueuedPage.selectedProperty().bindBidirectional(viewModel.queuedPageActiveProperty());
        btnFlowedPage.selectedProperty().bindBidirectional(viewModel.flowedPageActiveProperty());

        cmbZoom.getItems().setAll(Arrays.asList(0.25f, 0.5f, 0.8f, 1f, 1.2f, 1.5f, 2f, 3f, 4f));
        cmbZoom.setConverter(new FloatPercentageConverter());
        cmbZoom.valueProperty().bindBidirectional(viewModel.zoomProperty());

        viewModel.pdfProperty().addListener(o -> refreshPageView());
        viewModel.zoomProperty().addListener(o -> refreshPageView());
        viewModel.currentPageProperty().addListener(o -> refreshPageView());
        viewModel.pageViewTypeProperty().addListener(o -> refreshPageView());

        btnPagePrevious.disableProperty().bind(viewModel.firstPageProperty());
        btnPageNext.disableProperty().bind(viewModel.lastPageProperty());

        lblPageCount.textProperty().bind(viewModel.pageCountProperty().asString());
        lblPageCurrent.textProperty().bind(viewModel.currentPageProperty().add(1).asString());
    }

    private void refreshPageView() {
        imgSinglePagePDF.setImage(null);
        pnlQueuedPage.getChildren().clear();
        pnlFlowedPage.getChildren().clear();
        pdfRenderer = null;

        if (viewModel.getPdf() == null || viewModel.getPageViewType() == null)
            return;

        JfxUiThreadPool.submit(() -> {
            try {
                pdfRenderer = new PDFRenderer(viewModel.getPdf());

                switch (viewModel.getPageViewType()) {
                    case SinglePage:
                        final WritableImage image = SwingFXUtils.toFXImage(pdfRenderer.renderImage(viewModel.getCurrentPage(), viewModel.getZoom()), null);
                        Platform.runLater(() -> imgSinglePagePDF.setImage(image));
                        break;
                    case QueuedPage:
                        refreshPDFAllPages(pdfRenderer, pnlQueuedPage, node -> VBox.setMargin(node, new Insets(5)));
                        break;
                    case FlowedPage:
                        refreshPDFAllPages(pdfRenderer, pnlFlowedPage, node -> FlowPane.setMargin(node, new Insets(5)));
                        break;
                    default:
                        throw new RuntimeException();
                }
            } catch (IOException e) {
                LOGGER.error("unable to load PDF page", e);

                Platform.runLater(() -> {
                    imgSinglePagePDF.setImage(null);
                    pnlQueuedPage.getChildren().clear();
                    pnlFlowedPage.getChildren().clear();
                    pdfRenderer = null;
                    new Alert(Alert.AlertType.ERROR, "Unable to load PDF page", ButtonType.OK).showAndWait();
                });
            }
        });
    }

    private void refreshPDFAllPages(final PDFRenderer pdfRenderer, final Pane pane, final Consumer<Node> marginSetter) throws IOException {
        for (int i=0; i<viewModel.getPdf().getPages().getCount(); i++) {
            final WritableImage image = SwingFXUtils.toFXImage(pdfRenderer.renderImage(i, viewModel.getZoom()), null);
            Platform.runLater(() -> {
                final ImageView imageView = new ImageView(image);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                marginSetter.accept(imageView);
                imageView.setEffect(new DropShadow());
                pane.getChildren().add(imageView);
            });
        }
    }

    @FXML
    private void onActionSave(ActionEvent e) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("lan/text");

        final Optional<File> file = FXChooserUtils.showFileSaveChooser(resourceBundle.getString("cmp.pdf.save"), viewModel.getFile(), new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        if (file.isPresent()) {
            try {
                viewModel.getPdf().save(file.get());
                viewModel.setFilename(file.get().getAbsolutePath());
            } catch (IOException e1) {
                LOGGER.error("unable to save PDF", e1);
                new Alert(Alert.AlertType.ERROR, resourceBundle.getString("cmp.pdf.save.error"), ButtonType.OK).showAndWait();
            }
        }
    }

    @FXML
    private void onActionOpen(ActionEvent e) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("lan/text");

        final Optional<File> file = FXChooserUtils.showFileOpenChooser(resourceBundle.getString("cmp.pdf.open"), viewModel.getFile(), new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        if (file.isPresent()) {
            try {
                viewModel.setPdf(PDDocument.load(file.get()));
                viewModel.setFilename(file.get().getAbsolutePath());
            } catch (IOException e1) {
                LOGGER.error("unable to, load PDF", e1);
                new Alert(Alert.AlertType.ERROR, resourceBundle.getString("cmp.pdf.open.error"), ButtonType.OK).showAndWait();
            }
        }
    }

    @FXML
    private void onActionPagePrevious(ActionEvent e) {
        viewModel.setCurrentPage(viewModel.getCurrentPage() - 1);
    }

    @FXML
    private void onActionPageNext(ActionEvent e) {
        viewModel.setCurrentPage(viewModel.getCurrentPage() + 1);
    }

    @FXML
    private void onScroll(ScrollEvent e) {
        if (e.isControlDown()) {
            e.consume();

            if (e.getDeltaY() == 0)
                return;

            final float factor = e.getDeltaY() > 0 ? ZOOM_FACTOR : -ZOOM_FACTOR;
            viewModel.setZoom(viewModel.getZoom() + factor);
        }
    }

    private Integer[] toArray(final int count) {
        final Integer[] array = new Integer[count];
        for (int i=0; i<count; i++) {
            array[i] = i;
        }

        return array;
    }
}
