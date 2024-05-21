package millionaire;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 * Main class to start the game.
 */
public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {
        JFrame frame = new JFrame("Who Wants to be a Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setVisible(true);
        
        Database data = new Database();
        data.userDataTable();
        data.leaderboardTable();

        // Starting the game loop if needed
        // Start.start();
    }
}
