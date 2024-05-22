package millionaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Panel extends JPanel {

    private JButton startButton;
    private JButton rulesButton;
    private JButton leaderboardButton;
    private JButton feedbackButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Panel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(new Color(0x1e1e1e));

        // Create buttons
        startButton = createButton("Start", 100, 80);
        rulesButton = createButton("Rules", 100, 180);
        leaderboardButton = createButton("Leaderboard", 100, 280);
        feedbackButton = createButton("Feedback", 100, 380);

        // Add buttons to the panel
        add(startButton);
        add(rulesButton);
        add(leaderboardButton);
        add(feedbackButton);

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "UserDataPage");
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RulesDisplay");
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Database data = new Database();
                System.out.println("leaderboard was clicked");
                try {
                    Leaderboard.displayLeaderboard();
                } catch (SQLException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                cardLayout.show(mainPanel, "LeaderboardPanel");
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "FeedbackDisplay");
            }
        });

        setBorder(BorderFactory.createLineBorder(Color.GREEN, 3)); // Set green border
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setForeground(Color.GREEN);
        button.setBackground(new Color(0x007BFF));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);

        // Set green border
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN, 4); // Green border, size 4
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Empty border size 10
        button.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.ORANGE);
        g.drawString("Who Wants to be a Millionaire", ((getSize().width - 250) / 2), 30);
    }
}
