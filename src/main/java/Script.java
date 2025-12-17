import managers.ScriptManager;
import screens.components.Image;

import java.awt.*;

public class Script extends ScriptManager {

    @Override
    public void run() {

        /* =========================
           INITIALISATION
        ========================= */

        initButtons();

        Image arriereplan = background("bg");
        arriereplan.setScale(1.9f);
        Image arriereplan2 = background("bg2");
        arriereplan2.setScale(1.9f);


        Image rika = image("rika");
        rika.setPosition(600, 400);
        rika.setScale(1.5f);

        Chara Rika = character("Rika", Color.BLUE);

        //playSound("theme");
        fadeShow(arriereplan, 400);

        /* =========================
           INTRODUCTION
        ========================= */

        speak(null, "La nuit est tombée depuis longtemps. La ville dort, mais pas Rika.");
        speak(null, "Dans cette chambre silencieuse, une seule pensée tourne en boucle.");

        instantPass();
        fadeShow(rika, 400);

        fadeHide(arriereplan, 400);
        fadeShow(arriereplan2, 400);


        speak(Rika, "Encore ce rêve...");
        speak(Rika, "Toujours le même. Toujours cette sensation de chute.");

        /* =========================
           PREMIER MOMENT DE DOUTE
        ========================= */
        instantPass();
        updateScale(rika, 1.7f);

        speak(null, "Rika détourne le regard, comme si quelque chose l'observait.");
        speak(Rika, "Est-ce que je deviens folle ?");
        speak(Rika, "Ou est-ce que je commence enfin à comprendre ?");

        /* =========================
           RÉVÉLATION
        ========================= */

        updateScale(rika, 2.2f);

        speak(null, "Un souvenir refait surface. Clair. Précis.");
        speak(Rika, "Ce jour-là...");
        speak(Rika, "On m’a dit que tout irait bien.");
        speak(Rika, "Mais personne n’a jamais demandé si je voulais savoir la vérité.");

        /* =========================
           CONFLIT INTÉRIEUR
        ========================= */

        instantPass();
        updateScale(rika, 2.6f);

        speak(null, "Le silence devient pesant.");
        speak(null, "Rika serre les poings.");

        speak(Rika, "Si je ferme les yeux, tout redeviendra normal.");
        speak(Rika, "Mais si je les ouvre...");
        speak(Rika, "Je ne pourrai plus jamais faire semblant.");

        /* =========================
           DÉCISION
        ========================= */

        updateScale(rika, 3.0f);

        speak(null, "Elle inspire profondément.");
        speak(Rika, "D’accord.");
        speak(Rika, "Je veux savoir.");
        speak(Rika, "Même si ça fait mal.");

        /* =========================
           CONCLUSION
        ========================= */

        fadeHide(arriereplan2, 400);

        speak(null, "À cet instant précis, quelque chose change.");
        speak(null, "Pas dans le monde.");
        speak(null, "Mais en elle.");

        speak(Rika, "Quoi qu’il arrive maintenant...");
        speak(Rika, "Je ne reculerai plus.");

        speak(null, "Fin.");

    }

}
