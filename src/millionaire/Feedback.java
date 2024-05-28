/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author azmaa
 */
public class Feedback {

    private static final String FILE = "Feedback.txt"; //feedback

    public static void feedback(String feedbackInput) throws IOException {
        // Generates date and formats it
        LocalDateTime now = LocalDateTime.now();
        // formating dd/mm/yy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yy");
        String date = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.newLine();
            writer.write("User Feedback " + date);
            writer.newLine();
            writer.write("  " + feedbackInput);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error Writing user feedback to file: " + e.getMessage());
        }
    }

}
