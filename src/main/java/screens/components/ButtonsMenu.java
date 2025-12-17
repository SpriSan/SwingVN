package screens.components;

import core.Engine;
import managers.ScriptManager;

import javax.swing.*;
import java.awt.*;
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

        // bouton viande
        JButton auto = new JButton("Auto");
        auto.setBounds(0, 0, 100, 40);
        auto.setContentAreaFilled(false);
        auto.setBorderPainted(true);
        auto.setOpaque(false);
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
        skip.setContentAreaFilled(false);
        skip.setBorderPainted(true);
        skip.setOpaque(false);
        skip.addActionListener(e -> {
            Engine.getInstance().isSkipOn = !Engine.getInstance().isSkipOn;
        });
        buttons.add(skip);

        // bouton logs
        JButton logs = new JButton("Logs");
        logs.setBounds(0, 40, 100, 40);
        logs.setContentAreaFilled(false);
        logs.setBorderPainted(true);
        logs.setOpaque(false);
        logs.addActionListener(e -> {
            Engine.getInstance().logs();
        });
        buttons.add(logs);

        // bouton back
        JButton menu = new JButton("Menu");
        menu.setBounds(0, 120, 100, 40);
        menu.setContentAreaFilled(false);
        menu.setBorderPainted(true);
        menu.setOpaque(false);
        buttons.add(menu);

        for (JButton btn : buttons) {
            add(btn);
            height += 40;
        }
    }
}
