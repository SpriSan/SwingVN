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
        removeAll();

        Text character = new Text(charName.name, 30, charName.color);
        character.setBounds(x, y - 30, 500, 50);
        add(character);

        JTextArea textArea = new JTextArea(text);
        textArea.setBounds(x, y, 1000, 200);
        textArea.setFont(new Font("Arial", Font.PLAIN, 30));
        textArea.setForeground(Color.black);
        textArea.setBackground(new Color(0, 0, 0, 0)); // Transparent
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        add(textArea);

        revalidate();
        repaint();
    }


}
