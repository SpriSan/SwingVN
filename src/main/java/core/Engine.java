package core;

import managers.ScriptManager;
import managers.ScriptManager.Chara;
import screens.Novel;
import screens.components.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {

    public static final Engine INSTANCE = new Engine();

    private final Map<String, Color> characters = new HashMap<>();


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
    }

    public void showImage(Image img) {

    }

    public void hideImage(Image img) {
    }

    public void registerCharacter(String name, Color color) {
        characters.put(name, color);
    }

    public void play(ScriptManager script) {
        script.run();
        novel.refreshImages();
    }


}
