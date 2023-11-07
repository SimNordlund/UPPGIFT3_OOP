import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonsMouseListener extends MouseAdapter { //Adapterklass

    private JButton gameButton;

    //Konstruktor
    public ButtonsMouseListener(JButton button) {
        this.gameButton = button;
    }

    //Sköter förändring av knappar vid exited, hover etc.
    @Override
    public void mouseEntered(MouseEvent e) {
        gameButton.setForeground(Color.red);
        gameButton.setBackground(Color.pink);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gameButton.setForeground(Color.black);
        gameButton.setBackground(Color.orange);
    }
}
