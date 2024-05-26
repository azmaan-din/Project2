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
            UserDataManager userFileHandler = new UserDataManager();
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
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while accessing the database: " + e.getMessage());
        }
    }
}

