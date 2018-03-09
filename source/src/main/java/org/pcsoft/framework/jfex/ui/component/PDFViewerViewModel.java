package org.pcsoft.framework.jfex.ui.component;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.pcsoft.framework.jfex.property.BooleanEnumerationProperty;
import org.pcsoft.framework.jfex.property.ConstraintNumberProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class PDFViewerViewModel implements ViewModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFViewerViewModel.class);

    //Content Property
    private final StringProperty filename = new SimpleStringProperty();
    private final ObjectBinding<File> file;

    //Behavior Properties
    private final BooleanProperty zoomReset = new SimpleBooleanProperty(true);

    //Internal Properties
    private final ObjectProperty<PDDocument> pdf = new SimpleObjectProperty<>();
    private final BooleanProperty singlePageActive = new SimpleBooleanProperty(), queuedPageActive = new SimpleBooleanProperty(), flowedPageActive = new SimpleBooleanProperty();

    //View Properties
    private final BooleanProperty showToolbar = new SimpleBooleanProperty(true);
    private final IntegerProperty currentPage = new SimpleIntegerProperty();
    private final ObjectProperty<Float> zoom = new ConstraintNumberProperty<>(new ConstraintNumberProperty.RangeConstraint<>(0.1f, 4f));
    private final ObjectProperty<PDFViewer.PageViewType> pageViewType = new BooleanEnumerationProperty<>(
            PDFViewer.PageViewType.SinglePage,
            new BooleanEnumerationProperty.Value<>(PDFViewer.PageViewType.SinglePage, singlePageActive),
            new BooleanEnumerationProperty.Value<>(PDFViewer.PageViewType.QueuedPage, queuedPageActive),
            new BooleanEnumerationProperty.Value<>(PDFViewer.PageViewType.FlowedPage, flowedPageActive)
    );
    private final BooleanProperty showContentControl = new SimpleBooleanProperty(true), showOpenContent = new SimpleBooleanProperty(true);
    private final BooleanProperty showViewControl = new SimpleBooleanProperty(true), showPageViewControl = new SimpleBooleanProperty(true);

    //Info Properties
    private final BooleanBinding documentOpened;
    private final IntegerBinding pageCount;
    private final BooleanBinding firstPage, lastPage;

    public PDFViewerViewModel() {
        //Internal Properties
        pdf.addListener((v, o, n) -> {
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    LOGGER.debug("unable to close pdf", e);
                }
            }

            if (n != null) {
                currentPage.set(0);
                if (zoomReset.get()) {
                    zoom.set(1f);
                }
            }
        });

        //Info Properties
        documentOpened = pdf.isNotNull();
        pageCount = Bindings.createIntegerBinding(() -> {
            if (pdf.get() == null)
                return 0;

            return pdf.get().getPages().getCount();
        }, pdf);
        firstPage = pdf.isNull().or(currentPage.isEqualTo(0));
        lastPage = pdf.isNull().or(currentPage.isEqualTo(pageCount.subtract(1)));

        file = Bindings.createObjectBinding(() -> filename.get() == null ? null : new File(filename.get()), filename);
    }

    //region Internal Used
    public boolean isSinglePageActive() {
        return singlePageActive.get();
    }

    public BooleanProperty singlePageActiveProperty() {
        return singlePageActive;
    }

    public void setSinglePageActive(boolean singlePageActive) {
        this.singlePageActive.set(singlePageActive);
    }

    public boolean isQueuedPageActive() {
        return queuedPageActive.get();
    }

    public BooleanProperty queuedPageActiveProperty() {
        return queuedPageActive;
    }

    public void setQueuedPageActive(boolean queuedPageActive) {
        this.queuedPageActive.set(queuedPageActive);
    }

    public boolean isFlowedPageActive() {
        return flowedPageActive.get();
    }

    public BooleanProperty flowedPageActiveProperty() {
        return flowedPageActive;
    }

    public void setFlowedPageActive(boolean flowedPageActive) {
        this.flowedPageActive.set(flowedPageActive);
    }

    public PDDocument getPdf() {
        return pdf.get();
    }

    public ObjectProperty<PDDocument> pdfProperty() {
        return pdf;
    }

    public void setPdf(PDDocument pdf) {
        this.pdf.set(pdf);
    }

    public File getFile() {
        return file.get();
    }

    public ObjectBinding<File> fileProperty() {
        return file;
    }

    //endregion


    public boolean isShowPageViewControl() {
        return showPageViewControl.get();
    }

    public BooleanProperty showPageViewControlProperty() {
        return showPageViewControl;
    }

    public void setShowPageViewControl(boolean showPageViewControl) {
        this.showPageViewControl.set(showPageViewControl);
    }

    public boolean isShowContentControl() {
        return showContentControl.get();
    }

    public BooleanProperty showContentControlProperty() {
        return showContentControl;
    }

    public void setShowContentControl(boolean showContentControl) {
        this.showContentControl.set(showContentControl);
    }

    public boolean isShowOpenContent() {
        return showOpenContent.get();
    }

    public BooleanProperty showOpenContentProperty() {
        return showOpenContent;
    }

    public void setShowOpenContent(boolean showOpenContent) {
        this.showOpenContent.set(showOpenContent);
    }

    public boolean isShowViewControl() {
        return showViewControl.get();
    }

    public BooleanProperty showViewControlProperty() {
        return showViewControl;
    }

    public void setShowViewControl(boolean showViewControl) {
        this.showViewControl.set(showViewControl);
    }

    public ObjectProperty<PDFViewer.PageViewType> pageViewTypeProperty() {
        return pageViewType;
    }

    public void setPageViewType(PDFViewer.PageViewType pageViewType) {
        this.pageViewType.set(pageViewType);
    }

    public PDFViewer.PageViewType getPageViewType() {
        return pageViewType.get();
    }

    public Boolean getDocumentOpened() {
        return documentOpened.get();
    }

    public BooleanBinding documentOpenedProperty() {
        return documentOpened;
    }

    public Number getPageCount() {
        return pageCount.get();
    }

    public IntegerBinding pageCountProperty() {
        return pageCount;
    }

    public String getFilename() {
        return filename.get();
    }

    public StringProperty filenameProperty() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename.set(filename);
    }

    public boolean isShowToolbar() {
        return showToolbar.get();
    }

    public BooleanProperty showToolbarProperty() {
        return showToolbar;
    }

    public void setShowToolbar(boolean showToolbar) {
        this.showToolbar.set(showToolbar);
    }

    public int getCurrentPage() {
        return currentPage.get();
    }

    public IntegerProperty currentPageProperty() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage.set(currentPage);
    }

    public float getZoom() {
        return zoom.get();
    }

    public ObjectProperty<Float> zoomProperty() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom.set(zoom);
    }

    public boolean isZoomReset() {
        return zoomReset.get();
    }

    public BooleanProperty zoomResetProperty() {
        return zoomReset;
    }

    public void setZoomReset(boolean zoomReset) {
        this.zoomReset.set(zoomReset);
    }

    public Boolean getFirstPage() {
        return firstPage.get();
    }

    public BooleanBinding firstPageProperty() {
        return firstPage;
    }

    public Boolean getLastPage() {
        return lastPage.get();
    }

    public BooleanBinding lastPageProperty() {
        return lastPage;
    }
}
