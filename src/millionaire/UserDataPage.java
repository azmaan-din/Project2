/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

        JLabel ageLabel = new JLabel("Enter your last name:");
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
                String age = ageTextField.getText();
                System.out.println("User name entered: " + firstName);
                // You can add more actions here, like navigating to another page
            }
        });

    }
}
