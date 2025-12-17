package screens.components;

import core.Engine;
import managers.ScriptManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogsMenu extends JPanel {

    int x,y;
    int width,height;

    public LogsMenu(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setBackground(new Color(255, 255, 255, 200));
        setLayout(null);
        setVisible(false);
    }

    public void render(Map<ScriptManager.Chara, String> logs) {
        removeAll();

        if (isVisible()) {
            int yPosition = 10;

            for (Map.Entry<ScriptManager.Chara, String> entry : logs.entrySet()) {
                ScriptManager.Chara chara = entry.getKey();
                String text = entry.getValue();

                if (text == null) {
                    continue;
                }

                String charName = "";
                Color charColor = Color.BLACK;

                if (chara != null) {
                    charName = (chara.name != null) ? chara.name : "";
                    charColor = (chara.color != null) ? chara.color : Color.BLACK;
                }

                Text name = new Text(charName, 20, charColor);
                name.setBounds(10, yPosition, getWidth() - 20, 30);
                add(name);

                yPosition += 35;

                Text dialog = new Text(text, 16, Color.BLACK);
                dialog.setBounds(20, yPosition, getWidth() - 30, 50);
                add(dialog);

                yPosition += 60;
            }

            JButton button = new JButton("Back");
            button.setBounds(10, getHeight() - 50 - yPosition, getWidth() - 30, 50);
            button.addActionListener(e -> {
                Engine.getInstance().logs();
            });
            add(button);
        }

        revalidate();
        repaint();
    }
}