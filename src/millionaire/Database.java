/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author azmaa
 */
public class Database {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public Database() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void userDataTable() {
        String userdataTableSQl = "CREATE TABLE USERDATA ("
                + "USERID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "FIRSTNAME VARCHAR(50), "
                + "LASTNAME VARCHAR(50), "
                + "AGE INT, "
                + "MONEY INT)";

        try {
            statement = conn.createStatement();
            statement.executeUpdate(userdataTableSQl);
            System.out.println("USERDATA table created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void leaderboardTable() {
        String leaderboardTableSQl = "CREATE TABLE LEADERBOARD ("
                + "LEADERBOARDID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "USERID INT, "
                + "FIRSTNAME VARCHAR(50), "
                + "LASTNAME VARCHAR(50), "
                + "MONEY INT, "
                + "FOREIGN KEY (USERID) REFERENCES USERDATA(USERID))";

        try {
            statement = conn.createStatement();
            statement.executeUpdate(leaderboardTableSQl);
            System.out.println("Leaderboard table created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void viewTableData(String tableName) {
        String query = "SELECT * FROM " + tableName;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            int columnCount = rs.getMetaData().getColumnCount();
            System.out.println("Data in " + tableName + " table:");

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
