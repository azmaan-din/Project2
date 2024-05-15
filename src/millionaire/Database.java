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
                + "USERID INT PRIMARY KEY, "
                + "FIRSTNAME VARCHAR(50), "
                + "LASTNAME VARCHAR(50), "
                + "AGE INT, "
                + "MONEY INT)";

        try {
            statement = conn.createStatement();
            statement.executeUpdate(userdataTableSQl);
            System.out.println("BOOK table created and records inserted.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dropTableIfExists(String tableName) {
        try {
            if (conn != null && statement != null) {
                ResultSet rs = conn.getMetaData().getTables(null, null, tableName.toUpperCase(), null);
                if (rs.next()) {
                    statement.executeUpdate("DROP TABLE " + tableName);
                    System.out.println("Table " + tableName + " dropped.");
                }
            } else {
                System.err.println("Failed to drop table: Connection or Statement is null.");
            }
        } catch (SQLException e) {
            System.out.println("DropTableIfExists had an error" + e);
        }
    }

}
