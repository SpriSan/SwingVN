package screens.components;

import managers.ResourceRegister;

import javax.swing.*;
import java.awt.*;

public class Image extends JPanel {

    public String name;
    public int x, y;
    double scale = 1f;

    private int baseWidth;
    private int baseHeight;

    int w, h;

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
        updateBounds();
    }

    public void setScale(double scale) {
        this.scale = scale;
        w = (int) (baseWidth * scale);
        h = (int) (baseHeight * scale);
        updateBounds();
    }

    private void updateBounds() {

        setBounds(x, y, w, h);
        setPreferredSize(new Dimension(w, h));
        setSize(w, h);

        revalidate();
        repaint();
    }

    public void setBackground() {
        Container parent = this.getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, parent.getComponentCount() - 1);
            parent.repaint();
        }
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

        java.awt.Image img = ResourceRegister.getImage(name);
        if (img != null) {
            g2d.translate(w / 2, h / 2);

            g2d.drawImage(img, -w / 2, -h / 2, w, h, this);
        }
    }
}