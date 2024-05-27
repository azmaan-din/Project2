package millionaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * LeaderboardPanel class for displaying the leaderboard.
 */
public class LeaderboardPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Data> leaderboardData;
    private JPanel leaderboardContainer;
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);
    private static final Color FIRST_PLACE_COLOUR = new Color(0xFFD700);
    private static final Color SECOND_PLACE_COLOUR = new Color(0xC0C0C0);
    private static final Color THIRD_PLACE_COLOUR = new Color(0xCD7F32);

    public LeaderboardPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(BACKGROUND_COLOUR);

        JButton backButton = createButton("Back", 20, 20);
        add(backButton);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "InitialPanel"));

        leaderboardContainer = new JPanel();
        leaderboardContainer.setLayout(null);
        leaderboardContainer.setBackground(BACKGROUND_COLOUR);

        JScrollPane scrollPane = new JScrollPane(leaderboardContainer);
scrollPane.setBounds(20, 80, 600, 400);  // Updated width from 460 to 600
scrollPane.setBorder(BorderFactory.createEmptyBorder());
scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
scrollPane.getVerticalScrollBar().setUnitIncrement(16);
add(scrollPane);

refreshLeaderboard();

    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 40);
        button.setForeground(TEXT_COLOUR);
        button.setBackground(BUTTON_COLOUR);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER_COLOUR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOUR);
            }
        });

        return button;
    }

    private void fetchLeaderboardData() throws SQLException {
        UserDataManager userFileHandler = new UserDataManager();
        leaderboardData = userFileHandler.getLeaderboardData();
    }

    public void refreshLeaderboard() {
        try {
            fetchLeaderboardData();
            updateLeaderboard();
        } catch (SQLException e) {
            Logger.getLogger(LeaderboardPanel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void updateLeaderboard() {
        leaderboardContainer.removeAll();

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBounds(0, 0, 460, 40);
        leaderboardContainer.add(titleLabel);

        if (leaderboardData != null && !leaderboardData.isEmpty()) {
            int yPosition = 60;
            int rank = 1;

            for (Data userData : leaderboardData) {
                JPanel entryPanel = new JPanel();
                entryPanel.setLayout(null);
                entryPanel.setBackground(rank <= 3 ? new Color(0x333333) : new Color(0x202020));
                entryPanel.setBounds(10, yPosition, 440, 40);

                JLabel rankLabel = new JLabel(String.valueOf(rank), SwingConstants.CENTER);
                rankLabel.setFont(new Font("Arial", Font.BOLD, 16));
                rankLabel.setForeground(rank <= 1 ? FIRST_PLACE_COLOUR : rank <= 2 ? SECOND_PLACE_COLOUR : rank <= 3 ? THIRD_PLACE_COLOUR : TEXT_COLOUR);
                rankLabel.setBounds(10, 5, 30, 30);
                entryPanel.add(rankLabel);

                JLabel nameLabel = new JLabel(userData.getFirstname() + " " + userData.getLastname(), SwingConstants.LEFT);
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                nameLabel.setForeground(rank <= 1 ? FIRST_PLACE_COLOUR : rank <= 2 ? SECOND_PLACE_COLOUR : rank <= 3 ? THIRD_PLACE_COLOUR : TEXT_COLOUR);
                nameLabel.setBounds(50, 5, 250, 30);
                entryPanel.add(nameLabel);

                JLabel moneyLabel = new JLabel("Money: " + userData.getMoney(), SwingConstants.RIGHT);
                moneyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                moneyLabel.setForeground(rank <= 1 ? FIRST_PLACE_COLOUR : rank <= 2 ? SECOND_PLACE_COLOUR : rank <= 3 ? THIRD_PLACE_COLOUR : TEXT_COLOUR);
                moneyLabel.setBounds(310, 5, 120, 30);
                entryPanel.add(moneyLabel);

                leaderboardContainer.add(entryPanel);

                yPosition += 50;
                rank++;
            }

            leaderboardContainer.setPreferredSize(new java.awt.Dimension(460, yPosition + 20));
        } else {
            JLabel noDataLabel = new JLabel("No data available.", SwingConstants.CENTER);
            noDataLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            noDataLabel.setForeground(TEXT_COLOUR);
            noDataLabel.setBounds(0, 60, 460, 30);
            leaderboardContainer.add(noDataLabel);
        }

        leaderboardContainer.revalidate();
        leaderboardContainer.repaint();
    }
}
