import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FifteenGame extends JFrame {

    private JPanel gameContainer = new JPanel(); //Behållare för komponenter
    private JPanel getGameContainerCenter = new JPanel();
    private JPanel getGameContainerSouth = new JPanel();
    private JButton gameButtonInvisible = new JButton();
    private JButton gameRestart = new JButton("Nytt spel");
    private List<JButton> gameButtons = new ArrayList<>();
    private List<String> randomNumbers = Arrays.asList("1", "2", "3", "4", "5", "6", //Lista för att blanda knappar
            "7", "8", "9", "10", "11", "12", "13", "14", "15");

    //Konstruktor
    public FifteenGame() {
        //Frågar om användaren vill blanda spelet.
        int userAnswer = JOptionPane.showConfirmDialog(null, "Vill du blanda brickorna?");
        if (userAnswer == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Ok! Blandar brickorna");
            Collections.shuffle(randomNumbers); //Blandar listan med nummer.
        }
        //Skapar upp spelbrickan,
        this.add(gameContainer);
        gameContainer.setLayout(new BorderLayout());
        gameContainer.add(getGameContainerCenter, BorderLayout.CENTER);
        gameContainer.add(getGameContainerSouth, BorderLayout.SOUTH);
        getGameContainerCenter.setLayout(new GridLayout(4, 4));
        getGameContainerSouth.setLayout(new FlowLayout());
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Skapar tomma knappar.
        for (int i = 0; i < 16; i++) {
            gameButtons.add(new JButton());
        }
        //Lägger till siffror, lyssnare och lägger in knappar i container.Knappar 1-15.
        for (int i = 0; i < 15; i++) {
            gameButtons.get(i).setText(randomNumbers.get(i));
            gameButtons.get(i).addMouseListener(new ButtonsMouseListener(gameButtons.get(i)));
            getGameContainerCenter.add(gameButtons.get(i));
            gameButtons.get(i).addActionListener(new ActionListenerButtons(gameButtons.get(i), gameButtonInvisible, gameButtons));
        }

        //Lägger in osynlig knapp i lista och i GameContainerCenter
        gameButtons.get(15).setText(""); //Sätter in en tom text.
        gameButtons.set(15, gameButtonInvisible);  //Lägger in knappen i listan på sista plats.
        getGameContainerCenter.add(gameButtonInvisible);
        gameButtonInvisible.setVisible(false);

        //Lägger in restart-knappen och lägger till ActionListener. Lägger in i och i GameContainerCenter.
        getGameContainerSouth.add(gameRestart);
        gameRestart.addActionListener(new ActionListenerRestart(gameRestart, randomNumbers, gameButtons, gameButtonInvisible));

        //Sätter färger, textstorlek etc på knappar.
        Font f = new Font("Serif", Font.BOLD, 18);
        gameRestart.setFont(f);
        gameRestart.setBackground(Color.yellow);
        getGameContainerCenter.setBackground(new Color(245, 245, 220));
        getGameContainerSouth.setBackground(new Color(245, 245, 220));
        for (int i = 0; i < gameButtons.size() - 1; i++) {
            gameButtons.get(i).setBackground(Color.orange);
            gameButtons.get(i).setFont(f);
        }
    }
    public static void main(String[] args) {
        FifteenGame fg = new FifteenGame();
    }
}

