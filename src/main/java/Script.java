    import managers.ScriptManager;
    import screens.components.Image;

    import java.awt.*;

    public class Script extends ScriptManager {

        @Override
        public void run() {

            Image arriereplan = image("nom");
            Image rika = image("rika");
            rika.setPosition(700, 100);
            Chara Personnage = character("Perso", Color.BLUE);

            speak(Personnage, "gdfdfdgfdgfdgfdgfdfdgfd");

        }

    }
