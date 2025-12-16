import screens.MainMenu;
import screens.Novel;

import javax.swing.*;

import core.Engine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    static int HEIGHT = 900;
    static int WIDTH = 1250;

    public static void main(String[] args) {

        Script script;

        JFrame frame = new JFrame("SwingVN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);

        // Panel conteneur avec letterboxing
        JPanel containerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Peindre le fond en noir
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        containerPanel.setLayout(null);
        containerPanel.setBackground(Color.BLACK);

        Novel novelPanel = new Novel();
        novelPanel.setBounds(0, 0, WIDTH, HEIGHT);
        containerPanel.add(novelPanel);
        frame.add(containerPanel);
        frame.setVisible(true);

        Engine.getInstance().novel = novelPanel;
        Engine.getInstance().play(new Script());
    }
}