package screens.components;

import javax.swing.*;

import managers.ScriptManager.Chara;

import java.awt.*;

public class TextBox extends JPanel {

    public int size;
    public int x , y;

    public TextBox(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        setLayout(null); 
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size * 5, size * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

    }

    public void updateLine(String text, Chara charName) {
        Text line = new Text(text, 30, Color.black); 
        line.setBounds(x, y, 500, 50);

        Text character = new Text(charName.name, 30, charName.color);
        character.setBounds(x, y - 30, 500, 50);

        add(line);
        add(character);

        revalidate();
        repaint();
    }


}
