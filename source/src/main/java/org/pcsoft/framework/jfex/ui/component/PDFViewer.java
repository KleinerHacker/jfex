package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;


public class PDFViewer extends VBox {
    public enum PageViewType {
        SinglePage,
        QueuedPage,
        FlowedPage
    }

    private final PDFViewerView controller;
    private final PDFViewerViewModel viewModel;

    public PDFViewer() {
        final ViewTuple<PDFViewerView, PDFViewerViewModel> viewTuple =
                FluentViewLoader.fxmlView(PDFViewerView.class).root(this)
                        .resourceBundle(ResourceBundle.getBundle("lan/text"))
                        .load();
        controller = viewTuple.getCodeBehind();
        viewModel = viewTuple.getViewModel();
    }

    /**
     * Load the file within the PDF document and setup the {@link #filenameProperty()}
     * @param file
     * @throws IOException
     */
    public void loadDocument(final File file) throws IOException {
        viewModel.setPdf(PDDocument.load(file));
        viewModel.setFilename(file.getAbsolutePath());
    }

    public void loadDocument(final File file, final String password) throws IOException {
        viewModel.setPdf(PDDocument.load(file, password));
    }

    /**
     * Load PDF document from given stream
     * @param in
     * @throws IOException
     */
    public void loadDocument(final InputStream in) throws IOException {
        viewModel.setPdf(PDDocument.load(in));
    }

    public void loadDocument(final InputStream in, final String password) throws IOException {
        viewModel.setPdf(PDDocument.load(in, password));
    }

    /**
     * Load PDF document from given byte blob
     * @param doc
     * @throws IOException
     */
    public void loadDocument(final byte[] doc) throws IOException {
        viewModel.setPdf(PDDocument.load(doc));
    }

    public void loadDocument(final byte[] doc, final String password) throws IOException {
        viewModel.setPdf(PDDocument.load(doc, password));
    }

    /**
     * Close current opened document, see {@link #documentOpenedProperty()}
     */
    public void closeDocument() {
        viewModel.setPdf(null);
    }

    public PageViewType getPageViewType() {
        return viewModel.getPageViewType();
    }

    public ObjectProperty<PageViewType> pageViewTypeProperty() {
        return viewModel.pageViewTypeProperty();
    }

    public void setPageViewType(PageViewType pageViewType) {
        viewModel.setPageViewType(pageViewType);
    }

    public String getFilename() {
        return viewModel.getFilename();
    }

    public StringProperty filenameProperty() {
        return viewModel.filenameProperty();
    }

    public void setFilename(String filename) {
        viewModel.setFilename(filename);
    }

    public int getCurrentPage() {
        return viewModel.getCurrentPage();
    }

    public IntegerProperty currentPageProperty() {
        return viewModel.currentPageProperty();
    }

    public void setCurrentPage(int currentPage) {
        viewModel.setCurrentPage(currentPage);
    }

    public float getZoom() {
        return viewModel.getZoom();
    }

    public ObjectProperty<Float> zoomProperty() {
        return viewModel.zoomProperty();
    }

    public void setZoom(float zoom) {
        viewModel.setZoom(zoom);
    }

    public boolean isZoomReset() {
        return viewModel.isZoomReset();
    }

    public BooleanProperty zoomResetProperty() {
        return viewModel.zoomResetProperty();
    }

    public void setZoomReset(boolean zoomReset) {
        viewModel.setZoomReset(zoomReset);
    }

    public Number getPageCount() {
        return viewModel.getPageCount();
    }

    public IntegerBinding pageCountProperty() {
        return viewModel.pageCountProperty();
    }

    public boolean isShowToolbar() {
        return viewModel.isShowToolbar();
    }

    public BooleanProperty showToolbarProperty() {
        return viewModel.showToolbarProperty();
    }

    public void setShowToolbar(boolean showToolbar) {
        viewModel.setShowToolbar(showToolbar);
    }

    public Boolean getFirstPage() {
        return viewModel.getFirstPage();
    }

    public BooleanBinding firstPageProperty() {
        return viewModel.firstPageProperty();
    }

    public Boolean getLastPage() {
        return viewModel.getLastPage();
    }

    public BooleanBinding lastPageProperty() {
        return viewModel.lastPageProperty();
    }

    public Boolean getDocumentOpened() {
        return viewModel.getDocumentOpened();
    }

    public BooleanBinding documentOpenedProperty() {
        return viewModel.documentOpenedProperty();
    }

    public boolean isShowContentControl() {
        return viewModel.isShowContentControl();
    }

    public BooleanProperty showContentControlProperty() {
        return viewModel.showContentControlProperty();
    }

    public void setShowContentControl(boolean showContentControl) {
        viewModel.setShowContentControl(showContentControl);
    }

    public boolean isShowOpenContent() {
        return viewModel.isShowOpenContent();
    }

    public BooleanProperty showOpenContentProperty() {
        return viewModel.showOpenContentProperty();
    }

    public void setShowOpenContent(boolean showOpenContent) {
        viewModel.setShowOpenContent(showOpenContent);
    }

    public boolean isShowViewControl() {
        return viewModel.isShowViewControl();
    }

    public BooleanProperty showViewControlProperty() {
        return viewModel.showViewControlProperty();
    }

    public void setShowViewControl(boolean showViewControl) {
        viewModel.setShowViewControl(showViewControl);
    }

    public boolean isShowPageViewControl() {
        return viewModel.isShowPageViewControl();
    }

    public BooleanProperty showPageViewControlProperty() {
        return viewModel.showPageViewControlProperty();
    }

    public void setShowPageViewControl(boolean showPageViewControl) {
        viewModel.setShowPageViewControl(showPageViewControl);
    }
}
