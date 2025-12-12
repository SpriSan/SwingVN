package screens.components;

import javax.swing.*;
import java.awt.*;

public class TextComponent extends JPanel {

    public String text;
    public int size;
    public int x , y;

    public TextComponent(String text, int size,  int x, int y) {
        this.text = text;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size * 5, size * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setFont(new Font("Arial", Font.BOLD, size));
        g.drawString(text, x, y);
    }

}
