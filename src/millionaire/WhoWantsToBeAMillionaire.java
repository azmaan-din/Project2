package millionaire;

/**
 *
 * @author Azmaan, Yash
 */
// Importing IOException class from java.io package

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;

public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {
        
        
        JFrame frame = new JFrame("Who Want to be a Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setSize(700,700);
        frame.setVisible(true);
        
        

        
        Database data = new Database();
        //calls create table 
        data.userDataTable();
        data.leaderboardTable();
        
        


        // Calling the start method  
        Start.Start();
    }

}
