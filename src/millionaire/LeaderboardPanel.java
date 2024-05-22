package millionaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * LeaderboardPanel class for displaying the leaderboard.
 */
public class LeaderboardPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Data> leaderboardData;

    public LeaderboardPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(new Color(0x17191a)); // Set background color to #17191a
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Set green border

        JButton backButton = createButton("Back", 20, 20);
        add(backButton);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "InitialPanel"));

        refreshLeaderboard();
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setForeground(Color.GREEN);
        button.setBackground(new Color(0x007BFF));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GREEN, 2), // Set green border
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0x0056b3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0x007BFF));
            }
        });

        return button;
    }

    private void fetchLeaderboardData() throws SQLException {
        UserFileHandler userFileHandler = new UserFileHandler();
        leaderboardData = userFileHandler.getLeaderboardData();
    }

    public void refreshLeaderboard() {
        try {
            fetchLeaderboardData();
            repaint();
        } catch (SQLException e) {
            Logger.getLogger(LeaderboardPanel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(new Font("TimesRoman", Font.BOLD, 20));
    g.setColor(Color.YELLOW);
    g.drawString("Leaderboard", ((getSize().width - 75) / 2), 30);

    g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
    int yPosition = 100;
    int rank = 1;

    if (leaderboardData != null) {
        for (Data userData : leaderboardData) {
            if (rank <= 3) {
                // Set the color to yellow for the top 3 users' names
                g.setColor(Color.YELLOW);
            } else {
                // Set the color to white for other users' names
                g.setColor(Color.WHITE);
            }

            String entry = rank + ". " + userData.getFirstname() + " " + userData.getLastname() + " - Money: " + userData.getMoney();
            g.drawString(entry, 50, yPosition);
            yPosition += 30;
            rank++;
        }
    } else {
        g.setColor(Color.WHITE);
        g.drawString("No data available.", 50, yPosition);
    }
}


}
