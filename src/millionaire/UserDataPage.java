/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UserDataPage extends JPanel {

    public UserDataPage(CardLayout cardLayout, JPanel mainPanel) {

        setLayout(null);
        JLabel firstNameLabel = new JLabel("Enter your first name:");
        firstNameLabel.setBounds(50, 50, 150, 30);
        add(firstNameLabel);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(200, 50, 150, 30);
        add(firstNameTextField);

        JLabel lastNameLabel = new JLabel("Enter your last name:");
        lastNameLabel.setBounds(50, 150, 150, 30);
        add(lastNameLabel);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(200, 150, 150, 30);
        add(lastNameTextField);

        JLabel ageLabel = new JLabel("Enter your age:");
        ageLabel.setBounds(50, 250, 150, 30);
        add(ageLabel);

        JTextField ageTextField = new JTextField();
        ageTextField.setBounds(200, 250, 150, 30);
        add(ageTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 300, 100, 40);
        add(submitButton);

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
                Player userData = null;
                userData = new Player(0, firstName, lastName, age, 0);
                UserFileHandler userFileHandler = new UserFileHandler();
                try {
                    userFileHandler.storeUserDataToDatabase(userData);
                    // You can add more actions here, like navigating to another page
                } catch (SQLException ex) {
                    Logger.getLogger(UserDataPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
