package millionaire;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main class to start the game.
 */
public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {
        // Prompt the user for their name
        String playerName = JOptionPane.showInputDialog(null, "Welcome to Who Wants to be a Millionaire!\nPlease enter your name:");
        
        // Validate if the player name is provided
        if(playerName == null || playerName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must provide a name to play the game.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the main game frame
        JFrame frame = new JFrame("Who Wants to be a Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and add the game panel
        Panel panel = new Panel();
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setVisible(true);
}
}
