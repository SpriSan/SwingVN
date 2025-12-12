package screens;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextComponent;

import javax.swing.JPanel;

import managers.ResourceRegister;
import screens.components.Text;

public class Novel extends JPanel {

    Text actualDialogue;

    public Novel() {

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(ResourceRegister.getImage("textbox"), 50, 50, this);
    }

    public void speak(String text){
        actualDialogue = new Text(text, 20, 20, 20);
        add(actualDialogue);
        repaint();
    }

}
