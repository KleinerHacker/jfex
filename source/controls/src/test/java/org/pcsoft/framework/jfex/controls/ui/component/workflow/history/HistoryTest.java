package org.pcsoft.framework.jfex.controls.ui.component.workflow.history;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;


public class HistoryTest {

    @Test
    public void test() throws Exception {
        final HistoryModel historyModel = new HistoryModel();
        final AtomicInteger value = new AtomicInteger(0);

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertFalse(historyModel.getCanUndo());
        Assert.assertNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(0, value.get());

        historyModel.execute(new Command() {
            @Override
            public void execute() {
                value.addAndGet(10);
            }

            @Override
            public void undo() {
                value.addAndGet(-10);
            }
        });

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(10, value.get());

        historyModel.execute(new Command() {
            @Override
            public void execute() {
                value.incrementAndGet();
            }

            @Override
            public void undo() {
                value.decrementAndGet();
            }
        });

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(11, value.get());

        historyModel.execute(new Command() {
            private int old;

            @Override
            public void execute() {
                old = value.get();
                value.set(-99);
            }

            @Override
            public void undo() {
                value.set(old);
            }
        });

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(-99, value.get());

        //*********************************************************************
        //*********************************************************************
        //*********************************************************************

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(11, value.get());

        historyModel.redo();

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(-99, value.get());

        //*********************************************************************
        //*********************************************************************
        //*********************************************************************

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(11, value.get());

        historyModel.execute(new Command() {
            private int old;

            @Override
            public void execute() {
                old = value.get();
                value.set(-999);
            }

            @Override
            public void undo() {
                value.set(old);
            }
        });

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(-999, value.get());

        //*********************************************************************
        //*********************************************************************
        //*********************************************************************

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(11, value.get());

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(10, value.get());

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertFalse(historyModel.getCanUndo());
        Assert.assertNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(0, value.get());

        historyModel.redo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(10, value.get());

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertFalse(historyModel.getCanUndo());
        Assert.assertNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(0, value.get());

        historyModel.execute(new Command() {
            private int old;

            @Override
            public void execute() {
                old = value.get();
                value.set(-1);
            }

            @Override
            public void undo() {
                value.set(old);
            }
        });

        Assert.assertFalse(historyModel.getCanRedo());
        Assert.assertTrue(historyModel.getCanUndo());
        Assert.assertNotNull(historyModel.getLastCommand());
        Assert.assertNull(historyModel.getNextCommand());
        Assert.assertEquals(-1, value.get());

        historyModel.undo();

        Assert.assertTrue(historyModel.getCanRedo());
        Assert.assertFalse(historyModel.getCanUndo());
        Assert.assertNull(historyModel.getLastCommand());
        Assert.assertNotNull(historyModel.getNextCommand());
        Assert.assertEquals(0, value.get());
    }
}
