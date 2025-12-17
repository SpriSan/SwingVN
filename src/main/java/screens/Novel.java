package screens;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

import core.Engine;
import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.components.ButtonsMenu;
import screens.components.LogsMenu;
import screens.components.TextBox;

public class Novel extends JPanel {

    TextBox textBox;
    ButtonsMenu buttonsMenu;
    ScriptManager scriptManager;
    public LogsMenu logs;

    private JPanel darkbg;

    public Novel() {

        setLayout(null);

        darkbg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (Engine.getInstance().darkBackground) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(new Color(0, 0, 0, Engine.getInstance().darkbackgroundOpacity));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        darkbg.setOpaque(false);

        textBox = new TextBox(1000, 100, 70, Engine.getInstance().topTextBox, Engine.getInstance().textColor);
        buttonsMenu = new ButtonsMenu(0, 0, 100);

        add(buttonsMenu);
        add(textBox);
        add(darkbg);
        setBackground(Color.BLACK);

        logs = new LogsMenu(0, 0, getWidth(), getHeight());
        add(logs);

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

                int logsWidth = (int) (width * 0.97);
                int logsHeight = (int) (height * 0.935);
                logs.setBounds(10, 10, logsWidth, logsHeight);
                darkbg.setBounds(0, 0, width, height);


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
    }

    public void speak(String text, Chara charName){
        textBox.updateLine(text, charName);
        repaint();
    }

    public void changeTextboxState(){
        textBox.setVisible(!textBox.isVisible());
        repaint();
    }

    public void changeLogsState() {
        logs.setVisible(!logs.isVisible());

        if (logs.isVisible()) {
            logs.render(Engine.getInstance().logs);
            setComponentZOrder(logs, 0);
        }

        buttonsMenu.setVisible(!buttonsMenu.isVisible());
        textBox.setVisible(false);

        revalidate();
        repaint();
    }

    public void forceChangeTextboxState(){
        if (!textBox.isVisible()) {
            textBox.setVisible(true);
        }
    }
}