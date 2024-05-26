package millionaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author azmaa
 */
public class UserDataPanel extends JPanel {

    private JButton submitButton;
    private JButton backButton;
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);
    private static final Color INPUT_COLOUR = new Color(0x8D99AE);

    public UserDataPanel(CardLayout cardLayout, JPanel mainPanel) {

        setLayout(null);
        setBackground(BACKGROUND_COLOUR);

        JLabel firstNameLabel = new JLabel("Enter your first name:");
        firstNameLabel.setBounds(50, 50, 150, 30);
        firstNameLabel.setForeground(TEXT_COLOUR);
        add(firstNameLabel);

        JTextField firstNameTextField = createTextField(200, 50);
        add(firstNameTextField);

        JLabel lastNameLabel = new JLabel("Enter your last name:");
        lastNameLabel.setBounds(50, 150, 150, 30);
        lastNameLabel.setForeground(TEXT_COLOUR);
        add(lastNameLabel);

        JTextField lastNameTextField = createTextField(200, 150);
        add(lastNameTextField);

        JLabel ageLabel = new JLabel("Enter your age:");
        ageLabel.setBounds(50, 250, 150, 30);
        ageLabel.setForeground(TEXT_COLOUR);
        add(ageLabel);

        JTextField ageTextField = createTextField(200, 250);
        add(ageTextField);

        submitButton = createButton("Submit", 300, 300);
        add(submitButton);
        backButton = createButton("Back", 0, 0);
        add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle submit button click
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                int age = 0;
                try {
                    age = Integer.parseInt(ageTextField.getText());
                } catch (NumberFormatException z) {
                    System.out.println("Invalid input: Please enter a valid number.");
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                Player userData = new Player(0, firstName, lastName, age, 0);
                UserDataManager userFileHandler = new UserDataManager();
                try {
                    userFileHandler.storeUserDataToDatabase(userData);
                    // Clear text fields
                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    ageTextField.setText("");
                    GamePanel gamePanel = new GamePanel(cardLayout, mainPanel, userData);
                    mainPanel.add(gamePanel, "GamePanel");
                    cardLayout.show(mainPanel, "GamePanel");
                } catch (SQLException ex) {
                    System.out.println("error in User Data Page");
                    Logger.getLogger(UserDataPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "InitialPanel");
            }
        });
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        textField.setBackground(INPUT_COLOUR);
        textField.setForeground(TEXT_COLOUR);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

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

}
