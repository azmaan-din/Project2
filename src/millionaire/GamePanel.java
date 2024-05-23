package millionaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePanel extends JPanel {

    private Player userData;
    private LinkedList<Questions> allQuestions;
    private int currentQuestionIndex;
    private int prizeMoney;
    private int questCount;
    private int round;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel moneyLabel;

    private JPanel circlePanel; // Panel to hold the circles
    private JButton[] circleButtons; // Array to hold the circle buttons
    private int circlesCompleted; // Counter to track completed circles

    private JPanel prizeMoneyPanel;
    int[] prizeMoneyValues = {2500, 7500, 17500, 37500, 77500, 157500, 317500, 637500};

    public GamePanel(CardLayout cardLayout, JPanel mainPanel, Player userData) {
        this.userData = userData;
        this.prizeMoney = 2500;
        this.questCount = 0;
        this.round = 1;
        this.currentQuestionIndex = 0;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(new Color(0x17191a)); // Set background color to #17191a
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Set green border

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setForeground(Color.GREEN);
        questionLabel.setBounds(50, 20, 600, 80);
        add(questionLabel);

        optionButtons = new JButton[4];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = createButton();
            optionButtons[i].setLayout(new FlowLayout(FlowLayout.LEFT)); // Align buttons to the left
            optionButtons[i].setPreferredSize(new Dimension(600, 40)); // Set preferred size to maintain width
            optionButtons[i].addActionListener(new OptionButtonListener());
            add(optionButtons[i]);
            // Adjust the y-coordinate to space out the buttons vertically
            optionButtons[i].setBounds(50, 100 + (i * 50), 300, 40);
        }


        moneyLabel = new JLabel("Current Money: $0");
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        moneyLabel.setForeground(Color.GREEN);
        moneyLabel.setBounds(50, 350, 600, 30);
        add(moneyLabel);

        // Initialize circles panel and buttons
        circlePanel = new JPanel();
        circlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Adjust spacing as needed
        circlePanel.setBounds(50, 400, 600, 50); // Adjust position and size as needed
        circlePanel.setOpaque(false); // Make the panel transparent
        add(circlePanel);

        circleButtons = new JButton[8]; // Assuming 8 circles
        for (int i = 0; i < circleButtons.length; i++) {
            circleButtons[i] = createCircleButton();
            circlePanel.add(circleButtons[i]);
        }

        circlesCompleted = 0; // Initialize completed circles counter

        // Initialize prize money panel
        // Initialize prize money panel
// Initialize prize money panel
prizeMoneyPanel = new JPanel();
prizeMoneyPanel.setLayout(new BoxLayout(prizeMoneyPanel, BoxLayout.Y_AXIS)); // Vertical layout
prizeMoneyPanel.setBounds(700, 70, 200, 450); // Adjust X position to move it to the right side
prizeMoneyPanel.setBackground(new Color(0x17191a)); // Set background color
prizeMoneyPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1 )); // Set green border
add(prizeMoneyPanel);



        for (int i = 0; i < prizeMoneyValues.length; i++) {
    JLabel prizeLabel = new JLabel("Round " + (i + 1) + ": $" + prizeMoneyValues[i]);
    prizeLabel.setForeground(Color.GREEN);
    prizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    prizeMoneyPanel.add(prizeLabel);
    if (i == round - 1) {
        prizeLabel.setForeground(Color.YELLOW); // Highlight current round
    }
    prizeMoneyPanel.add(Box.createVerticalStrut(10)); // Add vertical space between labels
}


        loadQuestions();

        if (!allQuestions.isEmpty()) {
            displayQuestion();
        }
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setForeground(Color.GREEN);
        button.setBackground(new Color(0x007BFF));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x0056b3), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x0056b3));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x007BFF));
            }
        });

        return button;
    }

    private JButton createCircleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20)); // Adjust size as needed
        button.setBackground(Color.WHITE); // Initially white
        button.setBorder(BorderFactory.createEmptyBorder()); // No border
        button.setFocusPainted(false); // Remove focus paint
        button.setEnabled(false); // Disable interaction
        return button;
    }

    private void loadQuestions() {
        try {
            allQuestions = Questions.createQuestionBank("question_bank.txt");
        } catch (IOException e) {
            System.err.println("Error reading question bank file: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error loading questions.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex < allQuestions.size()) {
            Questions question = allQuestions.get(currentQuestionIndex);
            questionLabel.setText("<html>" + question.getQuestion() + "</html>");
            LinkedList<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setVisible(true);
            }
            for (int i = options.size(); i < optionButtons.length; i++) {
                optionButtons[i].setVisible(false);
            }
        } else {
            endGame();
        }
    }

    private void checkAnswer(String userAnswer) {
        Questions question = allQuestions.get(currentQuestionIndex);
        if (question.getAnswer().equalsIgnoreCase(userAnswer)) {
            userData.updateMoney(prizeMoney);
            prizeMoney *= 2;
            moneyLabel.setText("Current Money: $" + userData.getMoney());
            questCount++;
            if (questCount == 2) {
                round++;
                if (round != 5) {
                    int response = JOptionPane.showConfirmDialog(this, "You have completed round " + (round - 1) + ".\nDo you wish to continue?", "Round Completed", JOptionPane.YES_NO_OPTION);
           
                if (response == JOptionPane.NO_OPTION) {
                        endGame();
                        return;
                    } else {
                        questCount = 0;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You finished all rounds!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    endGame();
                    return;
                }
            }
            currentQuestionIndex++;
            displayQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect answer!", "Game Over", JOptionPane.ERROR_MESSAGE);
            userData.setMoney(0);
            endGame();

            // Set the color of the current circle to red
            circleButtons[circlesCompleted].setBackground(Color.RED);
        }

        // Increment circlesCompleted and update circle color
        circlesCompleted++;
        circleButtons[circlesCompleted - 1].setBackground(Color.GREEN);
    }

  private void endGame() {
    UserFileHandler userFileHandler = new UserFileHandler();
    moneyLabel.setText("Current Money: $" + userData.getMoney());
    try {
        userFileHandler.storeUserDataToDatabase(userData);
        JOptionPane.showMessageDialog(this, "Game over! Your final winnings are: $" + userData.getMoney(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        cardLayout.show(mainPanel, "InitialPanel");
        LeaderboardPanel leaderboardPanel = (LeaderboardPanel) mainPanel.getComponent(mainPanel.getComponentZOrder(this) - 1);
        leaderboardPanel.refreshLeaderboard();
        
        // Update the prize money panel to display the current prize money
        if (round <= 4) {
            prizeMoney = 2500 * (int) Math.pow(2, round - 1);
            prizeMoneyPanel.removeAll(); // Clear existing labels
            prizeMoneyPanel.setBounds(700, 20 + (round - 1) * 60, 200, 450); // Adjusted position based on round number
            JLabel prizeLabel = new JLabel("Round " + round + ": $" + prizeMoney);
            prizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            if (round == 5) {
                prizeLabel.setForeground(Color.YELLOW); // Highlight the final round
            } else {
                prizeLabel.setForeground(Color.GREEN); // Reset font color to green for other rounds
            }
            prizeMoneyPanel.add(prizeLabel);
            prizeMoneyPanel.add(Box.createVerticalStrut(10)); // Add vertical space between labels
            prizeMoneyPanel.repaint(); // Repaint the panel to reflect changes
        }
    } catch (SQLException ex) {
        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, "An error occurred while storing user data.", ex);
    }
}



    private class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String userAnswer = source.getText();
            char firstChar = userAnswer.charAt(0);
            String firstCharString = Character.toString(firstChar);
            checkAnswer(firstCharString);
        }
    }
}