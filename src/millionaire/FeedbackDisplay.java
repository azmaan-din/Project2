package millionaire;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;

/**
 * A JPanel for displaying and submitting user feedback. Author: user
 */
public class FeedbackDisplay extends JPanel {

    private JTextArea feedbackTextArea;
    private JButton submitButton;
    private JButton mainMenuButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);
    private static final Color INPUT_COLOUR = new Color(0x8D99AE);

    public FeedbackDisplay(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOUR);

        feedbackTextArea = new JTextArea(10, 30);
        feedbackTextArea.setBackground(INPUT_COLOUR);
        feedbackTextArea.setForeground(TEXT_COLOUR);
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        add(scrollPane, BorderLayout.CENTER);

        submitButton = createButton("Submit Feedback");
        add(submitButton, BorderLayout.SOUTH);

        mainMenuButton = createButton("Main Menu");
        add(mainMenuButton, BorderLayout.NORTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedback = feedbackTextArea.getText().trim();
                if (!feedback.isEmpty()) {
                    try {
                        Feedback.feedback(feedback);
                        JOptionPane.showMessageDialog(
                                FeedbackDisplay.this,
                                "Thank you for your feedback!",
                                "Feedback",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        feedbackTextArea.setText("");
                        cardLayout.show(mainPanel, "InitialPanel");
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(
                                FeedbackDisplay.this,
                                "Error saving feedback. Please try again.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            FeedbackDisplay.this,
                            "Feedback cannot be empty.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

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
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x0056b3), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

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
