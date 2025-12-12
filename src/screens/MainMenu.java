package screens;

import screens.components.Text;

import javax.swing.*;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    public ArrayList<JPanel> content = new ArrayList<JPanel>();

    public MainMenu(){

        for (JPanel p : content){
            add(p);
        }
    }

}
