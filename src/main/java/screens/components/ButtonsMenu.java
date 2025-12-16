package screens.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ButtonsMenu extends JPanel {

    int x, y;
    int width, height;
    ArrayList<JButton> buttons = new ArrayList<>();

    public ButtonsMenu(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = 80;
        setLayout(new GridLayout(2, 1));
        setBounds(x, y, width, height);
        render();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * 5, height * 2);
    }

    public void render() {

        // bouton skip
        JButton skip = new JButton("Skip");
        skip.setBounds(0, 0, 100, 40);
        skip.setContentAreaFilled(false);
        skip.setBorderPainted(true);
        skip.setOpaque(false);
        buttons.add(skip);

        // bouton back
        JButton back = new JButton("Back");
        back.setBounds(0, 40, 100, 40);
        back.setContentAreaFilled(false);
        back.setBorderPainted(true);
        back.setOpaque(false);
        buttons.add(back);

        for (JButton btn : buttons) {
            add(btn);
            height += 40;
        }
    }
}
