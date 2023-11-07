import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActionListenerButtons implements ActionListener {

    private List<JButton> gameButtons;
    private JButton gameButton;
    private JButton gameButtonInvisible;

    //Konstruktor
    public ActionListenerButtons(JButton button, JButton invisibleButton, List<JButton> gameButtons) {
        this.gameButton = button;
        this.gameButtonInvisible = invisibleButton;
        this.gameButtons = gameButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container centerContainer = ((Container) e.getSource()).getParent();
        int indexOfButtonPressed = -1;
        int indexOfInvisibleButton = -1;

        //Letar fram indexen av den tomma rutan och den ruta som är tryckt på.
        for (int i = 0; i < centerContainer.getComponentCount(); i++) {
            if (e.getSource() == centerContainer.getComponent(i)) {
                indexOfButtonPressed = i;
            } else if (centerContainer.getComponent(i) == gameButtonInvisible) {
                indexOfInvisibleButton = i;
            }
        }

        //Kontrollerar om den tryckta knappen är i anslutning till den tomma rutan.
        if (e.getSource() == gameButton && indexOfButtonPressed == indexOfInvisibleButton - 1 ||
                e.getSource() == gameButton && indexOfButtonPressed == indexOfInvisibleButton + 1 ||
                e.getSource() == gameButton && indexOfButtonPressed == indexOfInvisibleButton - 4 ||
                e.getSource() == gameButton && indexOfButtonPressed == indexOfInvisibleButton + 4) {

            //Ser till så att den osynliga knappen byter plats med den knapp som användaren tryckt på.
            if (indexOfInvisibleButton > indexOfButtonPressed) {
                centerContainer.remove(indexOfButtonPressed);
                centerContainer.add(gameButtonInvisible, indexOfButtonPressed);
                centerContainer.add(gameButton, indexOfInvisibleButton);
            } else {
                centerContainer.remove(indexOfInvisibleButton);
                centerContainer.add(gameButton, indexOfInvisibleButton);
                centerContainer.add(gameButtonInvisible, indexOfButtonPressed);
            }
            centerContainer.validate();
            centerContainer.repaint();

            //Sorterar gameButtons så de stämmer överens med den visuella bilden av knapparna i spelet.
            JButton tempHolder = gameButtons.get(indexOfButtonPressed);
            gameButtons.set(indexOfButtonPressed, gameButtons.get(indexOfInvisibleButton));
            gameButtons.set(indexOfInvisibleButton, tempHolder);

            if (ButtonsInOrder()) {
                JOptionPane.showMessageDialog(null, "Grattis, du vann!");
            }
        }
    }

    //Håller koll på ordningen av brickorna. Returnerar true när de är i rad.
    private boolean ButtonsInOrder() {
        int expectedNumber = 1;

        for (int i = 0; i < gameButtons.size() - 1; i++) {
            String label = gameButtons.get(i).getText();
            if (!label.equals(String.valueOf(expectedNumber))) {
                return false;
            }
            expectedNumber++;
        }
        return true;
    }
}

