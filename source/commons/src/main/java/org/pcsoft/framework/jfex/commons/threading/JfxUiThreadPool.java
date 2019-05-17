package org.pcsoft.framework.jfex.commons.threading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public final class JfxUiThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(JfxUiThreadPool.class);
    private static final ExecutorService EXECUTOR_SERVICE;

    static {
        LOGGER.info("Create new cached thread pool");
        EXECUTOR_SERVICE = Executors.newCachedThreadPool(r -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);

            return thread;
        });
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return EXECUTOR_SERVICE.submit(task);
    }

    public static <T> Future<T> submit(Runnable task, T result) {
        return EXECUTOR_SERVICE.submit(task, result);
    }

    public static Future<?> submit(Runnable task) {
        return EXECUTOR_SERVICE.submit(task);
    }

    private JfxUiThreadPool() {
    }
}
