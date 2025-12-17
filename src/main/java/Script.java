    import managers.ScriptManager;
    import screens.components.Image;

    import java.awt.*;

    public class Script extends ScriptManager {

        @Override
        public void run() {

            initButtons(100);
            Image arriereplan = background("bg");
            arriereplan.setScale(1.9f);
            Image rika = image("rika");
            rika.setPosition(600, 400);
            rika.setScale(1.5f);
            Chara Personnage = character("Rika", Color.BLUE);
            Chara perso2 = character("Adolf", Color.GREEN);

            playSound("theme");

            fadeShow(arriereplan, 400);

            speak(null, "La NSA et la CIA ont obligé Intel et AMD a mettre des backdoor dans leur processeurs capables de fonctionner et d'envoyer des informations par ondes radios même quand l'appareil est éteint.");

            speak(perso2, "Yaaaa mein arbeit");

            fadeShow(rika, 400);
            speak(Personnage, "Pourquoi tu fais cette tête ?");
            updateScale(rika, 4f);
            speak(Personnage, "Caca");

        }

    }
