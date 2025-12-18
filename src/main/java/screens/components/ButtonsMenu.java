package screens.components;

import core.Engine;
import managers.ScriptManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ButtonsMenu extends JPanel {

    int x, y;
    int width, height;
    ArrayList<JButton> buttons = new ArrayList<>();
    boolean isAutoPressed;

    public ButtonsMenu(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = 140;
        setLayout(new GridLayout(buttons.size(), 1));
        setBounds(x, y, width, height);
        render();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * 5, height * 2);
    }

    public void render() {

        // bouton auto
        JButton auto = new JButton("Auto");
        auto.setBounds(0, 0, 100, 40);
        auto.setFont(new Font("Arial", Font.BOLD, 16)); // AJOUTÉ
        auto.setForeground(Color.WHITE);
        auto.setBackground(new Color(50, 50, 50, 200));
        auto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        auto.setFocusPainted(false); // AJOUTÉ

        auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                auto.setBackground(new Color(80, 80, 80, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                auto.setBackground(new Color(50, 50, 50, 200));
            }
        });

        auto.addActionListener(e -> {
            Engine.getInstance().isAutoOn = !Engine.getInstance().isAutoOn;
            isAutoPressed = !isAutoPressed;
            if (isAutoPressed) {
                System.out.println(Engine.getInstance().isAutoOn);
            }
        });
        buttons.add(auto);

        // bouton skip
        JButton skip = new JButton("Skip");
        skip.setBounds(0, 40, 100, 40);
        skip.setFont(new Font("Arial", Font.BOLD, 16)); // AJOUTÉ
        skip.setForeground(Color.WHITE);
        skip.setBackground(new Color(50, 50, 50, 200));
        skip.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        skip.setFocusPainted(false); // AJOUTÉ

        skip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skip.setBackground(new Color(80, 80, 80, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skip.setBackground(new Color(50, 50, 50, 200));
            }
        });

        skip.addActionListener(e -> {
            Engine.getInstance().isSkipOn = !Engine.getInstance().isSkipOn;
        });
        buttons.add(skip);

        // bouton logs
        JButton logs = new JButton("Logs");
        logs.setBounds(0, 80, 100, 40);
        logs.setFont(new Font("Arial", Font.BOLD, 16)); // AJOUTÉ
        logs.setForeground(Color.WHITE);
        logs.setBackground(new Color(50, 50, 50, 200));
        logs.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        logs.setFocusPainted(false); // AJOUTÉ

        logs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logs.setBackground(new Color(80, 80, 80, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logs.setBackground(new Color(50, 50, 50, 200));
            }
        });

        logs.addActionListener(e -> {
            Engine.getInstance().logs();
        });
        buttons.add(logs);

        // bouton menu
        JButton menu = new JButton("Menu");
        menu.setBounds(0, 120, 100, 40);
        menu.setFont(new Font("Arial", Font.BOLD, 16)); // AJOUTÉ
        menu.setForeground(Color.WHITE);
        menu.setBackground(new Color(50, 50, 50, 200));
        menu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        menu.setFocusPainted(false); // AJOUTÉ

        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setBackground(new Color(80, 80, 80, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setBackground(new Color(50, 50, 50, 200));
            }
        });

        buttons.add(menu);

        for (JButton btn : buttons) {
            add(btn);
            height += 40;
        }
    }
}
