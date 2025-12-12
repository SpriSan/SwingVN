package screens.components;

import managers.ResourceRegister;

import javax.swing.*;
import java.awt.*;

public class Image extends JPanel {

    public String name;
    public int x, y;

    public Image(String name) {
        this.name = name;
        this.x = 0;
        this.y = 0;

        java.awt.Image img = ResourceRegister.getImage(name);
        if (img != null) {
            setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));

            setSize(img.getWidth(null), img.getHeight(null));
        }
        

        setOpaque(false);
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(ResourceRegister.getImage(name), 0, 0, this);
        g.drawString("FDSSDFDSS", 0, 0);
    }
}