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


public class FeedbackDisplay extends JPanel {
    //user interface elements for feedbacktextArea, submit button, mainmenu,cardlayout and mainpanel
    private JTextArea feedbackTextArea;
    private JButton submitButton;
    private JButton mainMenuButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    //setting colours and fonts sizes to the feedback panel
    private static final Color BACKGROUND_COLOUR = new Color(0x2B2D42);
    private static final Color TEXT_COLOUR = new Color(0xEDF2F4);
    private static final Color BUTTON_COLOUR = new Color(0xEF233C);
    private static final Color BUTTON_HOVER_COLOUR = new Color(0xD90429);
    private static final Color INPUT_COLOUR = new Color(0x8D99AE);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 24); 
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20); 
    
    
    public FeedbackDisplay(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        
        //setting the background colour
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOUR);
        
        //setting the feedback areas backgorund, font size and colour
        feedbackTextArea = new JTextArea(10, 30);
        feedbackTextArea.setFont(TEXT_FONT); 
        feedbackTextArea.setBackground(INPUT_COLOUR);
        feedbackTextArea.setForeground(TEXT_COLOUR);
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        add(scrollPane, BorderLayout.CENTER);
        //placing the buttons and locations
        submitButton = createButton("Submit Feedback");
        add(submitButton, BorderLayout.SOUTH);
        mainMenuButton = createButton("Main Menu");
        add(mainMenuButton, BorderLayout.NORTH);
        
        //handle of the submit button for the feedback submission
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
    //creation of button with specific text
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(TEXT_COLOUR);
        button.setBackground(BUTTON_COLOUR);
        button.setFont(BUTTON_FONT); 
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x0056b3), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        // changes colour of mosue when hovering over the text area
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
