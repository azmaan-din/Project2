package millionaire;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A JPanel for displaying and submitting user feedback.
 * Author: user
 */
public class FeedbackDisplay extends JPanel {

    private JTextArea feedbackTextArea;
    private JButton submitButton;

    public FeedbackDisplay() {
        setLayout(new BorderLayout());

        feedbackTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        add(scrollPane, BorderLayout.CENTER);

        submitButton = new JButton("Submit Feedback");
        add(submitButton, BorderLayout.NORTH);

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
                        feedbackTextArea.setText("");  // Clear the text area after submission
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
    }
}
