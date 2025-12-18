package managers;

import core.Engine;

import java.util.ArrayList;
import java.util.List;

import managers.ChoiceOption;

public class ChoiceManager {

    private List<ChoiceOption> choiceOptions = new ArrayList<>();
    private ScriptManager scriptManager;
    public String question = "";

    public ChoiceManager(ScriptManager scriptManager, String question) {
        this.scriptManager = scriptManager;
        this.question = question;
    }

    public ChoiceManager option(String text, String targetLabel) {
        choiceOptions.add(new ChoiceOption(text, targetLabel));
        return this;
    }

    public void build() {
        CommandManager cmd = () -> {
            Engine.getInstance().displayChoices(choiceOptions, question ,(selectedIndex) -> {

                String targetLabel = choiceOptions.get(selectedIndex).targetLabel;

                if (!scriptManager.labels.containsKey(targetLabel)) {
                    System.err.println("ERREUR: Label '" + targetLabel + "' introuvable!");
                    return;
                }

                // Sauter au label
                scriptManager.dialogueIndex = scriptManager.labels.get(targetLabel);

                // Continuer l'exécution
                scriptManager.next();
            });
        };
        scriptManager.commands.add(cmd);
    }
}