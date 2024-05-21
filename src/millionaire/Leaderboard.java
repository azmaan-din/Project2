/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Azmaan, Yash
 */
public class Leaderboard {
    // method for leaderboard
    public static void displayLeaderboard() throws SQLException {
        try {
            List<Data> allUserData = new UserFileHandler().getAllUserData();

            if (allUserData.isEmpty()) {
                System.out.println("No user data found.");
            } else {
                // Sort the user data based on money earned (descending order)
                Collections.sort(allUserData, Comparator.comparingInt(Data::getMoney).reversed());

                // getting time and date
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH_mm_ss");
                String dateTimeString = now.format(formatter);

                String fileName = "Leaderboard_" + dateTimeString + ".txt"; 

                try (FileWriter writer = new FileWriter(fileName)) {
                    // title to the file
                    writer.write("Leaderboard\n"); 
                    // printing the leaderboard title
                    System.out.println("Leaderboard:");

                    int rank = 1;

                    // going through data to display leaderboard
                    for (Data userData : allUserData) {

                        String leaderboardEntryString = rank + ". " + userData.getFirstname() + " "
                                + userData.getLastname()
                                + " - Money: " + userData.getMoney() + "\n";
                        writer.write(leaderboardEntryString);

                        System.out.println(leaderboardEntryString);
                        // increasing rank by 1
                        rank++;
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading user data from the file or writing the leaderboard: "
                    + e.getMessage());
        }
    }

}
