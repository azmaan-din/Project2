package millionaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Panel extends JPanel {

    private JButton startButton;
    private JButton rulesButton;
    private JButton leaderboardButton;
    private JButton feedbackButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    //user interface elements 
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);

    public Panel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        //setting the background colour
        setBackground(BACKGROUND_COLOUR);

        // creatin the buttons
        startButton = createButton("Start", 100, 80);
        rulesButton = createButton("Rules", 100, 180);
        leaderboardButton = createButton("Leaderboard", 100, 280);
        feedbackButton = createButton("Feedback", 100, 380);
        add(startButton);
        add(rulesButton);
        add(leaderboardButton);
        add(feedbackButton);

        // Adding action listeners for each buttons
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
    }

    //creatinf the buttons and setting up the colour , font size and placement
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //selecting the font and font size and colour
        g.setFont(new Font("TimesRoman", Font.BOLD, 30)); // Set font size to 30
        g.setColor(Color.ORANGE);
        // title position
        int stringWidth = g.getFontMetrics().stringWidth("Who Wants to be a Millionaire");
        g.drawString("Who Wants to be a Millionaire", (getWidth() - stringWidth) / 2, 50);
    }
}
