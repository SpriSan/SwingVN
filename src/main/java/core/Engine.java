package core;

import managers.ResourceRegister;
import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.Novel;
import screens.components.Image;
import screens.components.SoundManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {

    public static final Engine INSTANCE = new Engine();

    private final Map<String, Color> characters = new HashMap<>();
    public Map<Chara, String> logs = new HashMap<>();
    private ScriptManager currentScript;
    public boolean isAutoOn = false;
    public boolean isSkipOn = false;
    public boolean isLogsOn = false;


    public static Engine getInstance() {
        return INSTANCE;
    }

    public List<Image> images = new ArrayList<>();
    public Novel novel;


    public void registerImage(Image img, boolean isBackground) {
        if (isBackground) {
            images.add(0, img); // Ajouter au début de la liste (arrière-plan)
        } else {
            images.add(img); // Ajouter à la fin (premier plan)
        }
    }

    public void displayDialogue(Chara character, String text) {
        novel.speak(text, character);
        logs.put(character, text);
    }

    public void showImage(Image img) {
        img.visible = true;
        img.repaint();
    }

    public void fadeShowImage(Image img, int milliseconds) {
        img.fadeShow(milliseconds);
        img.repaint();
    }

    public void fadeHideImage(Image img, int milliseconds) {
        img.fadeHide(milliseconds);
        img.repaint();
    }

    public void hideImage(Image img) {
        img.visible = false;
        img.repaint();
    }

    public void playAudio(String name) {
        InputStream audio = ResourceRegister.getAudio(name);
        SoundManager.PlaySound(audio);
    }

    public void logs() {
        novel.changeLogsState();
    }


    public void registerCharacter(String name, Color color) {
        characters.put(name, color);
    }

    public void play(ScriptManager script) {
        this.currentScript = script;
        script.run();

        if (currentScript.hasNext()) {
            currentScript.next();
            novel.refreshImages();
            novel.repaint();
        }

        novel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (currentScript.hasNext()) {
                        currentScript.next();
                        novel.forceChangeTextboxState();
                        novel.refreshImages();
                        novel.repaint();
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!novel.logs.isVisible()) novel.changeTextboxState();
                }
            }
        });
    }
}