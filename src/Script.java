import managers.ScriptManager;
import screens.components.Image;

import java.awt.*;

public class Script extends ScriptManager {

    @Override
    public void run() {

        Image arriereplan = image("nom");
        Chara Personnage = character("Perso", Color.BLUE);

        speak(Personnage, "gdfdfdgfdgfdgfdgfdfdgfd");

    }

}
