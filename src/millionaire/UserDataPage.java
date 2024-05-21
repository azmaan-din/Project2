/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.awt.CardLayout;
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

    }
}
