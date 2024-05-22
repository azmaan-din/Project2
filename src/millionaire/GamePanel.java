package millionaire;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


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

    public GamePanel(CardLayout cardLayout, JPanel mainPanel, Player userData) {
        this.userData = userData;
        this.prizeMoney = 2500;
        this.questCount = 0;
        this.round = 1;
        this.currentQuestionIndex = 0;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(null);
        setBackground(new Color(0x1e1e1e));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
        questionLabel.setBounds(50, 50, 600, 30);
        add(questionLabel);

        optionButtons = new JButton[4];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setBounds(50, 100 + (i * 50), 600, 30);
            optionButtons[i].addActionListener(new OptionButtonListener());
            add(optionButtons[i]);
        }

        moneyLabel = new JLabel("Current Money: $0");
        moneyLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
        moneyLabel.setBounds(50, 300, 600, 30);
        add(moneyLabel);

        loadQuestions();

        if (!allQuestions.isEmpty()) {
            displayQuestion();
        }
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
            questionLabel.setText(question.getQuestion());
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
        System.out.println(question.getAnswer());
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
        }
    }

    private void endGame() {
        UserFileHandler userFileHandler = new UserFileHandler();
        moneyLabel.setText("Current Money: $" + userData.getMoney());
        try {
            userFileHandler.storeUserDataToDatabase(userData);
            JOptionPane.showMessageDialog(this, "Game over! Your final winnings are: $" + userData.getMoney(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(mainPanel, "InitialPanel");
            Leaderboard.displayLeaderboard();
        } catch (SQLException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, "An error occurred while storing user data.", ex);
        }
        
    }
    /* If an oprion is clicked then grabs the first character of the string and send it to check answer */

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