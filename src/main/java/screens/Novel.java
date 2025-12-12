package screens;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextComponent;

import javax.swing.JPanel;

import core.Engine;
import managers.ResourceRegister;
import managers.ScriptManager.Chara;
import screens.components.Text;
import screens.components.TextBox;

public class Novel extends JPanel {

    TextBox textBox;

    public Novel() {
        
        setLayout(null);

        textBox = new TextBox(100, 100, 100);
        textBox.setBounds(350, 700, 1200, 300);
        add(textBox);
        

    }

    public void refreshImages() {

        for (screens.components.Image img : Engine.getInstance().images) {
            remove(img);
        }

        for (int i = Engine.getInstance().images.size() - 1; i >= 0; i--) {
            screens.components.Image img = Engine.getInstance().images.get(i);
            img.setBounds(img.x, img.y, img.getPreferredSize().width, img.getPreferredSize().height);
            add(img);
        }

        setComponentZOrder(textBox, 0);

        revalidate();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(ResourceRegister.getImage("textbox"), 50, 50, this);
    }

    public void speak(String text, Chara charName){
        textBox.updateLine(text, charName);
        repaint();
    }
}