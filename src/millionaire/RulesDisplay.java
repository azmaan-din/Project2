package millionaire;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A JPanel for displaying the game rules.
 * Author: user
 */
public class RulesDisplay extends JPanel {

    private JTextArea rulesTextArea;
    private JButton mainMenuButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RulesDisplay(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Set background color to black

        rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setForeground(Color.WHITE); // Set text color to white
        rulesTextArea.setBackground(Color.BLACK); // Set text area background to black
        add(rulesTextArea, BorderLayout.CENTER);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setForeground(Color.WHITE); // Set button text color to white
        mainMenuButton.setBackground(Color.BLUE); // Set button background color to blue
        add(mainMenuButton, BorderLayout.SOUTH);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "InitialPanel");
            }
        });

        loadRules();
    }

    private void loadRules() {
        StringBuilder rules = new StringBuilder();
        String gameRulesFilePath = "rules.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(gameRulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rules.append(line).append("\n");
            }
        } catch (IOException e) {
            rules.append("Rules could not be loaded.");
        }

        rulesTextArea.setText(rules.toString());
    }
}
