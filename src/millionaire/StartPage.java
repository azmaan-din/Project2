/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author azmaa
 */
public class StartPage extends JPanel {
    
    public StartPage(CardLayout cardLayout, JPanel mainPanel){
        
        setLayout(null);
        JLabel label = new JLabel("Enter your name:");
        label.setBounds(50, 50, 150, 30);
        add(label);
        
    }
}
