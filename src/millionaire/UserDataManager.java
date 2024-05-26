package millionaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azmaan, Yash
 */
// handles the reading and writing of user data
public class UserDataManager {

    private static final String INSERT_USER_SQL = "INSERT INTO USERDATA (FIRSTNAME, LASTNAME, AGE, MONEY) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE USERDATA SET LASTNAME = ?, AGE = ?, MONEY = ? WHERE FIRSTNAME = ?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM USERDATA";
    private static final String INSERT_LEADERBOARD_SQL = "INSERT INTO LEADERBOARD (USERID, FIRSTNAME, LASTNAME, MONEY) VALUES (?, ?, ?, ?)";
    private static final String CLEAR_LEADERBOARD_SQL = "DELETE FROM LEADERBOARD";

    private final Connection conn;

    public UserDataManager() {
        DBManager dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void storeUserDataToDatabase(Data userData) throws SQLException {
        boolean userExists = false;

        // Check if the user already exists
        String checkUserSql = "SELECT COUNT(*) FROM USERDATA WHERE FIRSTNAME = ?";
        try ( PreparedStatement checkStmt = conn.prepareStatement(checkUserSql)) {
            checkStmt.setString(1, userData.getFirstname());
            try ( ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    userExists = rs.getInt(1) > 0;
                }
            }
        }

        if (userExists) {
            // Update existing user
            try ( PreparedStatement updateStmt = conn.prepareStatement(UPDATE_USER_SQL)) {
                updateStmt.setString(1, userData.getLastname());
                updateStmt.setInt(2, userData.getAge());
                updateStmt.setInt(3, userData.getMoney());
                updateStmt.setString(4, userData.getFirstname());
                updateStmt.executeUpdate();
            }
        } else {
            // Insert new user
            try ( PreparedStatement insertStmt = conn.prepareStatement(INSERT_USER_SQL)) {
                insertStmt.setString(1, userData.getFirstname());
                insertStmt.setString(2, userData.getLastname());
                insertStmt.setInt(3, userData.getAge());
                insertStmt.setInt(4, userData.getMoney());
                insertStmt.executeUpdate();
            }
        }
    }

    public List<Data> getAllUserData() throws SQLException {
        List<Data> userDataList = new ArrayList<>();

        try ( PreparedStatement selectStmt = conn.prepareStatement(SELECT_ALL_USERS_SQL);  ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("USERID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int age = rs.getInt("AGE");
                int money = rs.getInt("MONEY");
                userDataList.add(new Data(userId, firstName, lastName, age, money));  // Assuming you have a constructor in Data class that accepts these parameters
            }
        }
        return userDataList;
    }

    public void insertLeaderboardData(Data userData) throws SQLException {
        try ( PreparedStatement insertStmt = conn.prepareStatement(INSERT_LEADERBOARD_SQL)) {
            insertStmt.setInt(1, userData.getUserId());
            insertStmt.setString(2, userData.getFirstname());
            insertStmt.setString(3, userData.getLastname());
            insertStmt.setInt(4, userData.getMoney());
            insertStmt.executeUpdate();
        }
    }

    public void clearLeaderboard() throws SQLException {
        try ( PreparedStatement clearStmt = conn.prepareStatement(CLEAR_LEADERBOARD_SQL)) {
            clearStmt.executeUpdate();
        }
    }

    public List<Data> getLeaderboardData() throws SQLException {
        List<Data> leaderboardDataList = new ArrayList<>();
        String selectLeaderboardSQL = "SELECT * FROM LEADERBOARD ORDER BY MONEY DESC";

        try ( PreparedStatement selectStmt = conn.prepareStatement(selectLeaderboardSQL);  ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                int leaderboardId = rs.getInt("LEADERBOARDID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int money = rs.getInt("MONEY");
                leaderboardDataList.add(new Data(leaderboardId, firstName, lastName, 0, money));
            }
        }
        return leaderboardDataList;
    }
}
