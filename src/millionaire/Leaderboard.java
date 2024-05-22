/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

/**
 *
 * @author azmaa
 */
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    // method for leaderboard
    public static void displayLeaderboard() throws SQLException {
        try {
            UserFileHandler userFileHandler = new UserFileHandler();
            List<Data> allUserData = userFileHandler.getAllUserData();

            if (allUserData.isEmpty()) {
                System.out.println("No user data found.");
            } else {
                // Sort the user data based on money earned (descending order)
                Collections.sort(allUserData, Comparator.comparingInt(Data::getMoney).reversed());

                // Clear existing leaderboard data
                userFileHandler.clearLeaderboard();

                // Insert sorted data into leaderboard table
                for (Data userData : allUserData) {
                    userFileHandler.insertLeaderboardData(userData);
                }

                // Print the leaderboard
                System.out.println("Leaderboard:");
                int rank = 1;
                for (Data userData : allUserData) {
                    System.out.println(rank + ". " + userData.getFirstname() + " " + userData.getLastname() + " - Money: " + userData.getMoney());
                    rank++;
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while accessing the database: " + e.getMessage());
        }
    }
        public static void printLeaderboard() throws SQLException {
        UserFileHandler userFileHandler = new UserFileHandler();
        List<Data> leaderboardData = userFileHandler.getLeaderboardData();

        System.out.println("Leaderboard from LEADERBOARD table:");
        int rank = 1;
        for (Data userData : leaderboardData) {
            System.out.println(rank + ". " + userData.getFirstname() + " " + userData.getLastname() + " - Money: " + userData.getMoney());
            rank++;
        }
    }
}

