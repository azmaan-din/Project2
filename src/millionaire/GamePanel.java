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
    private SquarePanel squarePanel; 
    private int squaresCompleted;
    
    //user interface colour elements
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);

    public GamePanel(CardLayout cardLayout, JPanel mainPanel, Player userData) {
        this.userData = userData;
        this.prizeMoney = 2500;
        this.questCount = 0;
        this.round = 1;
        this.currentQuestionIndex = 0;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        //setting background colour
        setLayout(null);
        setBackground(BACKGROUND_COLOUR);

        //customermizing the questiosn font and colour and placement
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setForeground(TEXT_COLOUR);
        questionLabel.setBounds(50, 20, 600, 80);
        add(questionLabel);
        
        //limiting the amount of options and choosing the placements
        //made a optiosn panel and arranged layout 
        optionButtons = new JButton[4];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = createButton();
            optionButtons[i].setBounds(0, 0, 180, 40);
            optionButtons[i].addActionListener(new OptionButtonListener());
        }
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 0, 10)); 
        optionsPanel.setBounds(100, 100, 250, 160); 
        optionsPanel.setOpaque(false); 
        add(optionsPanel);

        //placing the options button
        for (JButton optionButton : optionButtons) {
            optionsPanel.add(optionButton);
        }
        //placements of the money progress label, placements and font colour
        moneyLabel = new JLabel("Current Money: $0");
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        moneyLabel.setForeground(TEXT_COLOUR);
        moneyLabel.setBounds(50, 350, 600, 30);
        add(moneyLabel);

        // Initialize the SquarePanel 
        squarePanel = new SquarePanel();
        squarePanel.setBounds(50, 400, 600, 50); 
        add(squarePanel);
        squaresCompleted = 0; 
        loadQuestions();
        if (!allQuestions.isEmpty()) {
            displayQuestion();
        }
    }
    //creation of buttons
    private JButton createButton() {
        JButton button = new JButton();
        button.setForeground(TEXT_COLOUR);
        button.setBackground(BUTTON_COLOUR);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOUR);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOUR);
            }
        });

        return button;
    }
    //loading questions from question bank
    private void loadQuestions() {
        try {
            allQuestions = Questions.createQuestionBank("question_bank.txt");
        } catch (IOException e) {
            System.err.println("Error reading question bank file: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error loading questions.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //displaying the questions
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
    //check throught the answer
    private void checkAnswer(String userAnswer) {
        Questions question = allQuestions.get(currentQuestionIndex);
        boolean isCorrect = question.getAnswer().equalsIgnoreCase(userAnswer);
        if (isCorrect) {
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

            
            setBackground(Color.RED);
        }

        squarePanel.fillSquare(squaresCompleted, isCorrect);
        squaresCompleted++;
    }
    //ending of the game
    private void endGame() {
        UserDataManager userFileHandler = new UserDataManager();
        moneyLabel.setText("Current Money: $" + userData.getMoney());
        try {
            userFileHandler.storeUserDataToDatabase(userData);
            JOptionPane.showMessageDialog(this, "Game over! Your final winnings are: $" + userData.getMoney(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(mainPanel, "InitialPanel");

            // Get LeaderboardPanel and refresh it
            LeaderboardPanel leaderboardPanel = WhoWantsToBeAMillionaire.getLeaderboardPanel(mainPanel);
            if (leaderboardPanel != null) {
                leaderboardPanel.refreshLeaderboard();
            } else {
                Logger.getLogger(GamePanel.class.getName()).log(Level.WARNING, "LeaderboardPanel not found.");
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
