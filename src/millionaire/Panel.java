package millionaire;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Panel class for the main game interface.
 */
public class Panel extends JPanel {
    
    private JButton startButton;
    private JButton rulesButton;
    private JButton leaderboardButton;
    private JButton feedbackButton;
    private Rules rules;
    
    public Panel() {
        setLayout(null);
        
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
        
        rules = new Rules();
        
        try {
            rules.init();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Rules file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading rules file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("start was clicked");
            }
        });
        
        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Panel.this, rules.getGameRules(), "Game Rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("leaderboard was clicked");
            }
        });
        
        feedbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Panel.this, "Feedback functionality not yet implemented.", "Feedback", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Who Wants to be a Millionaire", ((getSize().width - 250) / 2), 30);
    }
}
