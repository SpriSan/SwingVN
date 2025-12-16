package screens.components;

import javax.swing.*;

import managers.ScriptManager.Chara;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextBox extends JPanel {

    public int size;
    public int x , y;

    public TextBox(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        setLayout(null);

        JButton skip = new JButton("Skip");
        skip.setBounds(x - size / 2, y - size / 2, size, size);
        add(skip);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size * 5, size * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public void updateLine(String text, Chara charName) {
        removeAll();

        Text character = new Text(charName.name, 30, charName.color);
        character.setBounds(x, y - 30, 500, 50);
        add(character);

        JTextArea textArea = new JTextArea("");
        textArea.setBounds(x, y, 1000, 200);
        textArea.setFont(new Font("Arial", Font.PLAIN, 30));
        textArea.setForeground(Color.black);
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

        // Démarrer le timer APRÈS avoir mis à jour l'interface
        int letterDelay = 10; // Augmenté pour plus de stabilité
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
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

}
