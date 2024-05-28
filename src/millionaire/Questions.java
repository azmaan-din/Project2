// @author Azmaan, Yash
package millionaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Questions {
    private String question;
    private LinkedList<String> options;
    private String correctAnswer;

    public Questions(String question, LinkedList<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // creating questions from a file
    public static LinkedList<Questions> createQuestionBank(String filePath) throws IOException {
        LinkedList<Questions> allQuestions = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String questionText = "";
            LinkedList<String> options = new LinkedList<>();
            String correctAnswer = "";

            String line;

            while ((line = reader.readLine()) != null) {

                // removing trailing whitespaces
                line = line.trim();

                // going over empty lines
                if (line.isEmpty()) {
                    continue;
                }

                if (line.endsWith("?")) {

                    if (!questionText.isEmpty() && !options.isEmpty()) {
                        allQuestions.add(new Questions(questionText, new LinkedList<>(options), correctAnswer));
                        questionText = "";
                        options.clear();
                        correctAnswer = "";
                    }

                    questionText = line.substring(0, line.length() - 1);
                } else if (line.matches("[a-zA-Z]\\).*")) {
                    // removing the asterisk
                    String optionWithoutAsterisk = line.replace("*", "");
                    options.add(optionWithoutAsterisk);
                    if (line.endsWith("*")) {
                        correctAnswer = line.substring(0, 1);
                    }
                }
            }

            // Add the last question (if any) to the question list
            if (!questionText.isEmpty() && !options.isEmpty()) {
                allQuestions.add(new Questions(questionText, new LinkedList<>(options), correctAnswer));
            }
        } catch (IOException e) {
            // Print the exception details for debugging
            e.printStackTrace();
        }

        return allQuestions;
    }

    // Getters for question, options, and answer
    public String getQuestion() {
        return question;
    }

    public LinkedList<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return correctAnswer;
    }
}
