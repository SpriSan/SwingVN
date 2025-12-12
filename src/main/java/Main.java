
import screens.MainMenu;
import screens.Novel;

import javax.swing.*;

import core.Engine;

import java.awt.*;

public class Main {

    static int HEIGHT = 1080;
    static int WIDTH = 1920;

    public static void main(String[] args) {

        Script script;

        JFrame frame = new JFrame("SwingVN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(true);
        frame.setVisible(true);
        Novel novelPanel = new Novel();
        frame.add(novelPanel);
        Engine.getInstance().novel = novelPanel;
        Engine.getInstance().play(new Script());
    }
}