/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millionaire;

import java.sql.Connection;
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
    
    public Database(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public void connectProject2DB(){
        String leaderboardTableSQl = "CREATE TABLE USERDATA ("
                + "USERID INT PRIMARY KEY, "
                + "FIRSTNAME VARCHAR(50), "
                + "LASTNAME VARCHAR(50), "
                + "AGE INT, "
                + "MONEY INT)";
        
                try {
            statement = conn.createStatement();
            statement.executeUpdate(leaderboardTableSQl);
            System.out.println("BOOK table created and records inserted.");
        } catch (SQLException e) {
                    System.out.println(e);
        }
    }
    
}
