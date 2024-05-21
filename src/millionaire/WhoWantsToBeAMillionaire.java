package millionaire;

/**
 *
 * @author Azmaan, Yash
 */
// Importing IOException class from java.io package
import java.io.IOException;
import java.sql.SQLException;

public class WhoWantsToBeAMillionaire {

    public static void main(String[] args) throws IOException, SQLException {

        Database test = new Database();
        
        test.userDataTable();
        test.leaderboardTable();
        
        


        // Calling the start method  
        Start.Start();
    }

}
