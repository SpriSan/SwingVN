package screens;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

import core.Engine;
import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.components.ButtonsMenu;
import screens.components.TextBox;

public class Novel extends JPanel {

    TextBox textBox;
    ButtonsMenu buttonsMenu;
    ScriptManager scriptManager;

    public Novel() {

        setLayout(null);

        textBox = new TextBox(1000, 100, 70, true, Color.WHITE);
        buttonsMenu = new ButtonsMenu(0, 0, 100);

        add(buttonsMenu);
        add(textBox);
        setBackground(Color.BLACK);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                if (!textBox.isTop) {
                    int textBoxWidth = (int) (width * 0.8);
                    int textBoxHeight = (int) (height * 0.25);
                    int textBoxX = ((width - textBoxWidth) / 2) - 10;
                    int textBoxY = height - textBoxHeight - 20;
                    textBox.setBounds(textBoxX, textBoxY, textBoxWidth, textBoxHeight);
                } else {
                    int textBoxWidth = (int) (width * 0.8);
                    int textBoxHeight = (int) (height * 0.25);
                    int textBoxX = ((width - textBoxWidth) / 2) - 10;
                    int textBoxY = 0;
                    textBox.setOpaque(false);
                    textBox.setBounds(textBoxX, textBoxY, textBoxWidth, textBoxHeight);
                }

                //temporaire car pas fiable
                buttonsMenu.setBounds(width - buttonsMenu.getWidth() - 15, height - buttonsMenu.getHeight() -40, buttonsMenu.getWidth(), buttonsMenu.getHeight());
            }
        });
    }

    public void refreshImages() {
        for (screens.components.Image img : Engine.getInstance().images) {
            remove(img);
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        Dimension parentSize = new Dimension(panelWidth, panelHeight);

        for (int i = Engine.getInstance().images.size() - 1; i >= 0; i--) {
            screens.components.Image img = Engine.getInstance().images.get(i);

            img.updateParentSize(parentSize);

            img.setBounds(img.x, img.y, img.getPreferredSize().width, img.getPreferredSize().height);
            add(img);
            img.repaint();
        }

        setComponentZOrder(textBox, 0);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void speak(String text, Chara charName){
        textBox.updateLine(text, charName);
        repaint();
    }

    public void changeTextboxState(){
        textBox.setVisible(!textBox.isVisible());
        repaint();
    }

    public void forceChangeTextboxState(){
        if (!textBox.isVisible()) {
            textBox.setVisible(true);
        }
    }
}