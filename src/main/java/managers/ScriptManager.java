package managers;

import screens.Novel;
import screens.components.Image;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import core.Engine;

import javax.swing.*;
import javax.swing.text.Position;

public abstract class ScriptManager {

    public int dialogueIndex = 0;

    public ArrayList<CommandManager> commands = new ArrayList<>();

    public static class Chara {

        public final String name;
        public final Color color;
        public Chara(String name, Color color) {
            this.name = Objects.requireNonNull(name);
            this.color = Objects.requireNonNull(color);

        }

    }

    protected Image background(String name) {
        Image img = new Image(name);
        Engine.getInstance().registerImage(img, true); // true = background
        return img;
    }

    protected Image image(String name) {
        Image img = new Image(name);
        Engine.getInstance().registerImage(img, false); // false = foreground
        return img;

    }

    protected void playSound(String name) {
        CommandManager cmd = () -> {
            Engine.getInstance().playAudio(name);
            next();
        };
        commands.add(cmd);
    }

    protected void speak(Chara chr, String text) {
        CommandManager cmd = () -> Engine.getInstance().displayDialogue(chr, text);
        commands.add(cmd);
    }

    protected void hide(Image img) {
        CommandManager cmd = () -> Engine.getInstance().hideImage(img);
        commands.add(cmd);
    }

    protected void show(Image img) {
        CommandManager cmd = () -> Engine.getInstance().showImage(img);
        commands.add(cmd);
    }

    protected void fadeShow(Image img, int milliseconds) {
        CommandManager cmd = () -> Engine.getInstance().fadeShowImage(img, milliseconds);
        commands.add(cmd);
    }

    protected void fadeHide(Image img, int milliseconds) {
        CommandManager cmd = () -> Engine.getInstance().fadeHideImage(img, milliseconds);
        commands.add(cmd);
    }

    protected void updateScale(Image img, double scale) {
        CommandManager cmd = () -> img.setScale(scale);
        commands.add(cmd);
    }

    protected void updatePosition(Image img, int x, int y) {
        CommandManager cmd = () -> img.setPosition(x, y);
        commands.add(cmd);
    }

    protected void instantPass() {
        this.next();
        System.out.println("Passage instantané");

        SwingUtilities.invokeLater(() -> {
            Engine.getInstance().novel.refreshImages();
        });

        CommandManager cmd = this::next;
        commands.add(cmd);
    }

    protected Chara character(String name, Color color) {
        Chara c = new Chara(name, color);
        Engine.getInstance().registerCharacter(c.name, c.color);
        return c;
    }

    protected void noAction() {
        CommandManager cmd = this::noAction;
        commands.add(cmd);
    }

    public void next() {
        if (dialogueIndex < commands.size()) {
            commands.get(dialogueIndex++).execute();
            System.out.println("Evenement suivant");
        }
    }

    //fonction de merde ne faites pas ça
    public void initButtons(int autoPlayerDelay){
        ScheduledExecutorService autoPlayer = Executors.newSingleThreadScheduledExecutor();

        autoPlayer.scheduleAtFixedRate(() -> {
            //autplay
            while (Engine.getInstance().isAutoOn) {
                if (dialogueIndex < commands.size()) {
                    commands.get(dialogueIndex++).execute();
                    System.out.println("Evenement suivant en auto");
                    try {
                        Thread.sleep(autoPlayerDelay * 10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            //skip
            while (Engine.getInstance().isSkipOn) {
                if (dialogueIndex < commands.size()) {
                    commands.get(dialogueIndex++).execute();
                    System.out.println("Skip");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 0, 10, TimeUnit.MILLISECONDS);;
    }

    public boolean hasNext() {
        return dialogueIndex < commands.size();
    }

    public abstract void run();

}
