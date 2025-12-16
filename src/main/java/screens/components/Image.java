package screens.components;

import managers.ResourceRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Image extends JPanel {

    public String name;
    public int x, y;
    double scale = 1f;

    private int baseWidth;
    private int baseHeight;

    public boolean visible = false;

    public float opacity = 1f;

    int w, h;

    // Nouvelles propriétés pour le positionnement adaptatif
    private boolean useRelativePositioning = true;
    private double relativeX = 0.5; // Position X en pourcentage (0.0 à 1.0)
    private double relativeY = 0.5; // Position Y en pourcentage (0.0 à 1.0)
    private Dimension parentSize = null;

    public Image(String name) {
        this.name = name;

        java.awt.Image img = ResourceRegister.getImage(name);
        if (img != null) {
            baseWidth = img.getWidth(null);
            baseHeight = img.getHeight(null);

            w = (int) (baseWidth * scale);
            h = (int) (baseHeight * scale);

            this.x = 0;
            this.y = 0;

            setPreferredSize(new Dimension(w, h));
            setSize(w, h);
        }

        setOpaque(false);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.useRelativePositioning = false;
        updateBounds();
    }

    public void setRelativePosition(double relX, double relY) {
        this.relativeX = Math.max(0.0, Math.min(1.0, relX));
        this.relativeY = Math.max(0.0, Math.min(1.0, relY));
        this.useRelativePositioning = true;
        updatePositionFromRelative();
    }

    public void center() {
        setRelativePosition(0.5, 0.5);
    }

    public void updateParentSize(Dimension newSize) {
        this.parentSize = newSize;
        if (useRelativePositioning) {
            updatePositionFromRelative();
        }
    }

    private void updatePositionFromRelative() {
        if (parentSize != null) {

            this.x = (int)((parentSize.width - w) * relativeX);
            this.y = (int)((parentSize.height - h) * relativeY);
            updateBounds();
        }
    }

    public void setScale(double scale) {
        this.scale = scale;
        w = (int) (baseWidth * scale);
        h = (int) (baseHeight * scale);

        // Recalculer la position si en mode relatif
        if (useRelativePositioning) {
            updatePositionFromRelative();
        } else {
            updateBounds();
        }
    }

    public void scaleToFit(int maxWidth, int maxHeight) {
        double scaleX = (double)maxWidth / baseWidth;
        double scaleY = (double)maxHeight / baseHeight;
        setScale(Math.min(scaleX, scaleY));
    }

    public void scaleToCover(int minWidth, int minHeight) {
        double scaleX = (double)minWidth / baseWidth;
        double scaleY = (double)minHeight / baseHeight;
        setScale(Math.max(scaleX, scaleY));
    }

    private void updateBounds() {
        setBounds(x, y, w, h);
        setPreferredSize(new Dimension(w, h));
        setSize(w, h);

        revalidate();
        repaint();
    }

    public int getActualY() {
        return y;
    }

    public int getActualX() {
        return x;
    }

    public void setBackground() {
        Container parent = this.getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, parent.getComponentCount() - 1);
            parent.repaint();
        }
    }

    public void fadeShow(int milliseconds) {
        opacity = 0f;
        visible = true;
        int ms = 10;
        int steps = Math.max(1, milliseconds / ms);
        float increment = 1f / steps;
        Timer timer = new Timer(ms, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += increment;
                repaint();
                if (opacity >= 1f) {
                    opacity = 1f;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    public void fadeHide(int milliseconds) {
        opacity = 1f;
        int ms = 10;
        int steps = Math.max(1, milliseconds / ms);
        float increment = 1f / steps;
        Timer timer = new Timer(ms, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= increment;
                repaint();
                if (opacity <= 0f) {
                    opacity = 0f;
                    visible = false;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    public void setForeground() {
        Container parent = this.getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, 0);
            parent.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, opacity));

        java.awt.Image img = ResourceRegister.getImage(name);
        if (img != null && visible) {

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2d.translate(w / 2, h / 2);

            g2d.drawImage(img, -w / 2, -h / 2, w, h, this);
        }
    }
}