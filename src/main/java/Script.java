    import managers.ScriptManager;
    import screens.components.Image;

    import java.awt.*;

    public class Script extends ScriptManager {

        @Override
        public void run() {

            Image arriereplan = background("bg");
            arriereplan.setScale(1.9f);
            Image rika = image("rika");
            rika.setPosition(600, 100);
            rika.setScale(1.5f);
            Chara Personnage = character("Rika", Color.BLUE);


            speak(Personnage, "La NSA et la CIA ont obligé Intel et AMD a mettre des backdoor dans leur processeurs capables de fonctionner et d'envoyer des informations par ondes radios même quand l'appareil est éteint.");

            fadeHide(rika, 400);


            updateScale(rika, 3f);

            fadeShow(rika, 400);

            instantPass();
            speak(Personnage, "Pourquoi tu fais cette tête ?");
            speak(Personnage, "Caca");

        }

    }
