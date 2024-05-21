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
import javax.swing.JButton;

/**
 *
 * @author azmaa
 */
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
        setBackground(Color.BLACK); 

        startButton = new JButton("Start");
        startButton.setBounds(100, 80, 100, 40);
        add(startButton);

        rulesButton = new JButton("Rules");
        rulesButton.setBounds(100, 180, 100, 40);
        add(rulesButton);

        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setBounds(100, 280, 100, 40);
        add(leaderboardButton);

        feedbackButton = new JButton("Feedback");
        feedbackButton.setBounds(100, 380, 100, 40);
        add(feedbackButton);

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
                System.out.println("leaderboard was clicked");
            }
        });

        feedbackButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(mainPanel, "FeedbackDisplay");
    }
});


    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setForeground(Color.BLUE);  // Set text color to white
        button.setBackground(new Color(0x007BFF));  // Set button color (Bootstrap Primary)
        add(button);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.ORANGE);  // Set text color to white
        g.drawString("Who Want to be a Millionaire", ((getSize().width - 250) / 2), 30);
    }

}
