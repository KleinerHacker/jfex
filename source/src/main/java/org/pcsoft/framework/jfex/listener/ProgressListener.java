package org.pcsoft.framework.jfex.listener;

/**
 * Represent a listener for callback progress states
 */
public interface ProgressListener {
    /**
     * Progress has started
     */
    void onStartProgress();

    /**
     * Progress has finished
     */
    void onFinishProgress();

    /**
     * Progress was successfully finished. <b>Method {@link #onFinishProgress()} is always called, too.</b>
     */
    void onSuccess();

    /**
     * Progress was finished with failures. <b>Method {@link #onFinishProgress()} is always called, too.</b>
     * @param e
     */
    void onFailure(Throwable e);
}
