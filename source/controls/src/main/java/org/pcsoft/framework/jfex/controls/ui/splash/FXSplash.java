package org.pcsoft.framework.jfex.controls.ui.splash;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

/**
 * Splash Screen for FX
 */
public final class FXSplash extends JFrame {
    public static final class SplashHolder {
        private final FXSplash splash;

        private SplashHolder(FXSplash splash) {
            this.splash = splash;
        }

        public void dismiss() {
            splash.setVisible(false);
            splash.dispose();
        }
    }

    public static SplashHolder show(final boolean interminable, final Image splashImage, final String title,
                                final Image iconImage, final BiConsumer<Graphics2D, Rectangle> painter) {
        final FXSplash splash = new FXSplash(interminable, splashImage, title, iconImage, painter);
        splash.setVisible(true);
        splash.repaint();
        splash.requestFocus();

        return new SplashHolder(splash);
    }

    private final Image splashImage;
    private final BufferedImage paintImage;
    private final BiConsumer<Graphics2D, Rectangle> painter;

    private FXSplash(final boolean interminable, final Image splashImage, final String title,
                    final Image iconImage, final BiConsumer<Graphics2D, Rectangle> painter) throws HeadlessException {
        this.splashImage = splashImage;
        this.painter = painter;

        setTitle(title);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(new Dimension(splashImage.getWidth(null), splashImage.getHeight(null)));
        setIconImage(iconImage);
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2
        );
        setLayout(null);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !interminable) {
                    setVisible(false);
                    dispose();
                }
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!interminable) {
                    setVisible(false);
                    dispose();
                }
            }
        });

        final GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        paintImage = graphicsConfiguration.createCompatibleImage(splashImage.getWidth(this), splashImage.getHeight(this));
        paintSplash(paintImage.createGraphics(), new Rectangle(0, 0, splashImage.getWidth(this), splashImage.getHeight(this)));
    }

    @Override
    public final void paint(Graphics g) {
        super.paint(g);

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(paintImage, 0, 0, this);
    }

    private void paintSplash(Graphics g, Rectangle bounds) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(splashImage, 0, 0, this);

        painter.accept((Graphics2D) g, bounds);
    }
}
