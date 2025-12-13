package screens.components;

import javax.swing.*;
import java.awt.*;

public class Text extends JPanel {

    private final String text;
    private final int fontSize;
    private final Color color;

    public Text(String text, int fontSize, Color color) {
        this.text = text;
        this.fontSize = fontSize;
        this.color = color;

        setOpaque(false); 
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(text.length() * fontSize / 2, fontSize + 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.setColor(color);
        g.drawString(text, 0, fontSize); 
    }
}
