package screens;

import java.awt.*;
import javax.swing.JPanel;

import core.Engine;
import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.components.TextBox;

public class Novel extends JPanel {

    TextBox textBox;
    ScriptManager scriptManager;

    public Novel() {
        setLayout(null);

        textBox = new TextBox(100, 100, 70);
        add(textBox);
        setBackground(Color.BLACK);

        // Écouter les changements de taille
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                repositionComponents();
            }
        });
    }

    private void repositionComponents() {
        int width = getWidth();
        int height = getHeight();

        if (width <= 0 || height <= 0) return;

        // TextBox : centré horizontalement, en bas de l'écran
        int textBoxWidth = Math.min(1200, (int)(width * 0.8));  // Max 1200px ou 80% de la largeur
        int textBoxHeight = Math.min(300, (int)(height * 0.25)); // Max 300px ou 25% de la hauteur
        int textBoxX = (width - textBoxWidth) / 2;
        int textBoxY = height - textBoxHeight - 20; // 20px de marge en bas

        textBox.setBounds(textBoxX, textBoxY, textBoxWidth, textBoxHeight);

        // Mettre à jour la taille du parent pour toutes les images
        Dimension parentSize = new Dimension(width, height);
        for (screens.components.Image img : Engine.getInstance().images) {
            img.updateParentSize(parentSize);
        }

        revalidate();
        repaint();
    }

    public void refreshImages() {
        for (screens.components.Image img : Engine.getInstance().images) {
            remove(img);
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        Dimension parentSize = new Dimension(panelWidth, panelHeight);

        for (screens.components.Image img : Engine.getInstance().images) {
            // Informer l'image de la taille du parent
            img.updateParentSize(parentSize);

            // Ajouter l'image
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