package screens.components;

import managers.ResourceRegister;

import javax.swing.*;
import java.awt.*;

public class Image extends JPanel {

    public String name;

    public int x,y;

    public Image(String name) {
        this.name = name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(ResourceRegister.getImage(name), x, y, this);
    }

}
