package screens.components;

import javax.swing.*;

import core.Engine;
import managers.ScriptManager.Chara;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextBox extends JPanel {

    public int size;
    public int x , y;
    public boolean isTop;
    public Color color;

    public TextBox(int size, int x, int y, boolean isTop,  Color color) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.isTop = isTop;
        this.color = color;
        setLayout(null);

        JButton skip = new JButton("Skip");
        skip.setBounds(x - size / 2, y - size / 2, size, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public void updateLine(String text, Chara charName) {
        removeAll();

        String name = (charName == null) ? "" : charName.name;
        Color charColor = (charName == null) ? color : charName.color;

        Text character = new Text(name, 30, charColor);
        character.setBounds(x - 70, y - 50, 500, 50);
        add(character);

        JTextArea textArea = new JTextArea("");
        if (name.equals("")) {
            textArea.setBounds(x - 70, y-50, size - 50, 200);
        }
        else {
            textArea.setBounds(x - 70, y, size - 50, 200);
        }
        textArea.setFont(new Font("Arial", (Engine.getInstance().isBold) ? Font.BOLD : Font.PLAIN, 25));
        textArea.setForeground(color);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setHighlighter(null);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setOpaque(false);
        add(textArea);

        revalidate();
        repaint();

        StringBuilder gameText = new StringBuilder("");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        int[] index = {0};

        executor.scheduleAtFixedRate(() -> {
            if(index[0] < text.length()) {
                gameText.append(text.charAt(index[0]));
                index[0]++;
                SwingUtilities.invokeLater(() -> textArea.setText(gameText.toString()));
            } else {
                executor.shutdown();
            }
        }, 0, Engine.getInstance().textWritingSpeed, TimeUnit.MILLISECONDS);
    }

}
