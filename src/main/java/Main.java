import screens.MainMenu;
import screens.Novel;

import javax.swing.*;

import core.Engine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    static int HEIGHT = 1080;
    static int WIDTH = 1920;
    static final double ASPECT_RATIO = 16.0 / 9.0;

    public static void main(String[] args) {

        Script script;

        JFrame frame = new JFrame("SwingVN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(true);

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
        containerPanel.add(novelPanel);

        // Listener pour ajuster la taille du novelPanel
        containerPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int containerWidth = containerPanel.getWidth();
                int containerHeight = containerPanel.getHeight();

                double containerRatio = (double) containerWidth / containerHeight;

                int newWidth, newHeight, xOffset, yOffset;

                if (containerRatio > ASPECT_RATIO) {
                    // Conteneur plus large que 16:9 -> bandes noires sur les côtés
                    newHeight = containerHeight;
                    newWidth = (int) (newHeight * ASPECT_RATIO);
                    xOffset = (containerWidth - newWidth) / 2;
                    yOffset = 0;
                } else {
                    // Conteneur plus haut que 16:9 -> bandes noires en haut/bas
                    newWidth = containerWidth;
                    newHeight = (int) (newWidth / ASPECT_RATIO);
                    xOffset = 0;
                    yOffset = (containerHeight - newHeight) / 2;
                }

                novelPanel.setBounds(xOffset, yOffset, newWidth, newHeight);
            }
        });

        frame.add(containerPanel);
        frame.setVisible(true);

        Engine.getInstance().novel = novelPanel;
        Engine.getInstance().play(new Script());
    }
}