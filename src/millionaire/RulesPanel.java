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


public class RulesPanel extends JPanel {

    // Constants for UI customization
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final String GAME_RULES_FILE_PATH = "rules.txt";

    private JTextArea rulesTextArea;
    private JButton mainMenuButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RulesPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Setting layout and background color
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOUR);

        // Initialize components
        initializeComponents();

        // Add components to panel
        addComponentsToPanel();

        // Add event handlers
        addEventHandlers();

        // Load rules from file
        loadRules();
    }

    private void initializeComponents() {
        // Setting up the rules text area
        rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setForeground(TEXT_COLOUR);
        rulesTextArea.setBackground(BACKGROUND_COLOUR);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
        
        // Creating the main menu button
        mainMenuButton = createButton("Main Menu");
    }

    private void addComponentsToPanel() {
        // Adding components to the panel
        add(rulesTextArea, BorderLayout.CENTER);
        add(mainMenuButton, BorderLayout.SOUTH);
    }

    private void addEventHandlers() {
        // Event handler for the main menu button
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "InitialPanel");
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(TEXT_COLOUR);
        button.setBackground(BUTTON_COLOUR);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Adding hover effect to the button
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

    private void loadRules() {
        StringBuilder rules = new StringBuilder();

        // Reading rules from the file
        try (BufferedReader br = new BufferedReader(new FileReader(GAME_RULES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                rules.append(line).append("\n");
            }
        } catch (IOException e) {
            rules.append("Rules could not be loaded.");
        }

        // Setting the text in the rules text area
        rulesTextArea.setText(rules.toString());
    }
}
