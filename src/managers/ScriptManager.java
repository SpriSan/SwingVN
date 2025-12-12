package managers;

import core.Engine;
import screens.components.Image;

import java.awt.*;
import java.util.Objects;

public abstract class ScriptManager {

    public static class Chara {

        public final String name;
        public final Color color;
        public Chara(String name, Color color) {
            this.name = Objects.requireNonNull(name);
            this.color = Objects.requireNonNull(color);

        }

    }

    protected Image image(String name) {
        Image img = new Image(name);
        Engine.getInstance().registerImage(img);
        return img;
    }

    protected void speak(Chara chr, String text) {
        Engine.getInstance().displayDialogue(chr, text);
    }

    protected void hide(Image img) {
        Engine.getInstance().hideImage(img);
    }

    protected void show(Image img) {
        Engine.getInstance().showImage(img);
    }

    protected Chara character(String name, Color color) {
        Chara c = new Chara(name, color);
        Engine.getInstance().registerCharacter(c.name, c.color);
        return c;
    }


    public abstract void run();

}
