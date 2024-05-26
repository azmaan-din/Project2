package millionaire;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {

        JFrame frame = new JFrame("Who Wants to be a Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        Panel initialPanel = new Panel(cardLayout, mainPanel);
        UserDataPage userDataPage = new UserDataPage(cardLayout, mainPanel);
        RulesDisplay rulesDisplay = new RulesDisplay(cardLayout, mainPanel);
        FeedbackDisplay feedbackDisplay = new FeedbackDisplay(cardLayout, mainPanel);
        GamePanel gamePanel = new GamePanel(cardLayout, mainPanel, null);
        LeaderboardPanel leaderboardPanel = new LeaderboardPanel(cardLayout, mainPanel);

        mainPanel.add(initialPanel, "InitialPanel");
        mainPanel.add(userDataPage, "UserDataPage");
        mainPanel.add(rulesDisplay, "RulesDisplay");
        mainPanel.add(feedbackDisplay, "FeedbackDisplay");
        mainPanel.add(gamePanel, "GamePanel");
        mainPanel.add(leaderboardPanel, "LeaderboardPanel");

        leaderboardPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                leaderboardPanel.refreshLeaderboard();
            }
        });

        frame.add(mainPanel);

        cardLayout.show(mainPanel, "InitialPanel");

        frame.setVisible(true);

        Database data = new Database();
        data.userDataTable();
        data.leaderboardTable();
    }

    public static LeaderboardPanel getLeaderboardPanel(JPanel mainPanel) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof LeaderboardPanel) {
                return (LeaderboardPanel) comp;
            }
        }
        return null;
    }
}
