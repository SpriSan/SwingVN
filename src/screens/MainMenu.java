package screens;

import screens.components.Text;

import javax.swing.*;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    public ArrayList<JPanel> content = new ArrayList<JPanel>();

    public MainMenu(){

        Text texte = new Text("Test", 100, 150, 100);
        content.add(texte);

        for (JPanel p : content){
            add(p);
        }
    }

}
