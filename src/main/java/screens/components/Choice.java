package screens.components;

import core.Engine;
import managers.ChoiceOption; // IMPORTANT : Import de ChoiceOption

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Choice extends JPanel {

    public String question;
    public int height = 200;
    private ArrayList<ChoiceOption> options;
    private java.util.function.Consumer<Integer> onChoiceSelected;

    public Choice(List<ChoiceOption> options, String question, java.util.function.Consumer<Integer> callback) {
        this.options = new ArrayList<>(options);
        this.onChoiceSelected = callback;
        this.question = question;

        setLayout(null);
        setOpaque(false);
        render();
    }

    private void render() {
        int y = 0;

        if (question != null && !question.isEmpty()) {
            Text q = new Text(question, 20, Engine.getInstance().textColor);
            q.setBounds(0, y, 600, 30);
            add(q);
            y += 50;
        }

        for (int i = 0; i < options.size(); i++) {
            final int index = i;
            ChoiceOption option = options.get(index); // Changé

            JButton choiceButton = new JButton(option.text);

            choiceButton.setFont(new Font("Arial", Font.BOLD, 16));
            choiceButton.setForeground(Color.WHITE);
            choiceButton.setBackground(new Color(50, 50, 50, 200));
            choiceButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            choiceButton.setFocusPainted(false);

            choiceButton.setBounds(0, y, 600, 40);

            choiceButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    choiceButton.setBackground(new Color(80, 80, 80, 220));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    choiceButton.setBackground(new Color(50, 50, 50, 200));
                }
            });

            choiceButton.addActionListener(e -> {
                onChoiceSelected.accept(index);
            });

            add(choiceButton);

            y += 50;
            height = y + 20;
        }
    }
}