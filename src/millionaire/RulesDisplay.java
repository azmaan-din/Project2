package millionaire;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;

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
        setBackground(new Color(0x1e1e1e)); 

        rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setForeground(Color.GREEN); 
        rulesTextArea.setBackground(new Color(0x1e1e1e)); 
        add(rulesTextArea, BorderLayout.CENTER);

        mainMenuButton = createButton("Main Menu");
        add(mainMenuButton, BorderLayout.SOUTH);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "InitialPanel");
            }
        });

        loadRules();
    }
    
        private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.GREEN);
        button.setBackground(new Color(0x007BFF));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x0056b3), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0x0056b3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0x007BFF));
            }
        });

        return button;
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
