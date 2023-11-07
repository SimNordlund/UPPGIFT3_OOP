import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActionListenerRestart implements ActionListener {

    private List<JButton> gameButtons;
    private JButton restartGame;
    private List<String> randomNumbers;
    private JButton gameButtonInvisible;

    public ActionListenerRestart(JButton restartGame, List<String> listOfNumbers, List<JButton> gameButtons, JButton buttonInvisible) {
        this.restartGame = restartGame;
        this.randomNumbers = listOfNumbers;
        this.gameButtons = gameButtons;
        this.gameButtonInvisible = buttonInvisible;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Blandar om knapparna i spelet när man klickar på restart.
        Container centerContainer = gameButtonInvisible.getParent();
        if (e.getSource() == restartGame) {
            Collections.shuffle(randomNumbers); // Blandar numren i listan.

            JButton invisibleButtonTemp = null; //Skapar en variabel för att spara den osynliga knappen.

            //Tar bort knappen som är osynlig och sparar ner den.
            for (int i = 0; i < gameButtons.size(); i++) {
                JButton button = gameButtons.get(i);
                if (button.getText().isEmpty()) {
                    invisibleButtonTemp = button;
                    gameButtons.remove(i); //Tar bort knappen ifrån listan.
                    break;
                }
            }
            gameButtons.add(invisibleButtonTemp); //Lägger tillbaka knappen i listan så den hamnar sist.

            for (int i = 0; i < gameButtons.size() - 1; i++) {
                gameButtons.get(i).setText(randomNumbers.get(i)); // Uppdaterar knapparnas nummer.
            }

            //Tar bort osynliga knappen och lägger tillbaka den igen så att den hamnar sist
            centerContainer.remove(gameButtonInvisible);
            centerContainer.add(gameButtonInvisible);
        }
    }
}

