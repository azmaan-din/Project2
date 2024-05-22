/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author azmaa
 */
public class LeaderboardPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Data> leaderboardData;

    public LeaderboardPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(new Color(0x1e1e1e));

        JButton backButton = createButton("Back", 20, 20);
        add(backButton);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "InitialPanel"));

        try {
            fetchLeaderboardData();
        } catch (SQLException e) {
        }

    }
    
        private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x007BFF));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x0056b3), 1),
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.ORANGE);
        g.drawString("Leaderboard", ((getSize().width - 75) / 2), 30);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        int yPosition = 100;
        int rank = 1;

        if (leaderboardData != null) {
            for (Data userData : leaderboardData) {
                String entry = rank + ". " + userData.getFirstname() + " " + userData.getLastname() + " - Money: " + userData.getMoney();
                g.drawString(entry, 50, yPosition);
                yPosition += 30;
                rank++;
            }
        } else {
            g.drawString("No data available.", 50, yPosition);
        }
    }
}
