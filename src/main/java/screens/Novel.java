package screens;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import core.Engine;
import managers.ResourceRegister;
import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.components.Text;
import screens.components.TextBox;

public class Novel extends JPanel {

    TextBox textBox;
    ScriptManager scriptManager;

    public Novel() {

        setLayout(null);

        textBox = new TextBox(100, 100, 100);
        textBox.setBounds(350, 700, 1200, 300);
        add(textBox);
        setBackground(Color.BLACK);


    }


    public void refreshImages() {
        for (screens.components.Image img : Engine.getInstance().images) {
            remove(img);
        }

        for (screens.components.Image img : Engine.getInstance().images) {
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

        g.drawImage(ResourceRegister.getImage("textbox"), 50, 50, this);
    }

    public void speak(String text, Chara charName){
        textBox.updateLine(text, charName);
        repaint();
    }

    public void changeTextboxState(){
        if (textBox.isVisible()) {
            textBox.setVisible(false);
            repaint();
        }
        else {
            textBox.setVisible(true);
        }
    }

    public void forceChangeTextboxState(){
        if (!textBox.isVisible()) {
            textBox.setVisible(true);
        }
    }

}