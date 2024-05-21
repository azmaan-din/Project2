package millionaire;

/**
 *
 * @author Azmaan, Yash
 */
// Importing IOException class from java.io package

import java.awt.CardLayout;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {
        
        
        JFrame frame = new JFrame("Who Want to be a Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        
        Panel initialPanel = new Panel(cardLayout, mainPanel);
        UserDataPage userDataPage = new UserDataPage(cardLayout, mainPanel);
        RulesDisplay rulesDisplay = new RulesDisplay();
        FeedbackDisplay feedbackDisplay = new FeedbackDisplay(cardLayout, mainPanel); 
        
        
        mainPanel.add(initialPanel, "InitialPanel");
        mainPanel.add(userDataPage, "UserDataPage");
        mainPanel.add(rulesDisplay, "RulesDisplay");  
        mainPanel.add(feedbackDisplay, "FeedbackDisplay"); 

        
        
        frame.add(mainPanel);
        
        cardLayout.show(mainPanel, "InitialPanel");
        
        frame.setVisible(true);
        

        
        Database data = new Database();
        //calls create table 
        data.userDataTable();
        data.leaderboardTable();
        
      
    }

}
