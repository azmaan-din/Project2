package millionaire;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Azmaan, Yash
 */
public class CollectUserData {
    public static Player collectUserData(Scanner scanner) throws SQLException {
        Player userData = null;
        System.out.println("Before We Start Please Enter Your Information Please");
        System.out.println("First Name: ");
        // ask for the user first name
        String firstname = scanner.nextLine();
        // ask for the user last name
        System.out.println("Last Name: ");
        String lastname = scanner.nextLine();
        int age = 0;
        boolean validAgeInput = false;
        while (!validAgeInput) {
            try {
                // asking for the age
                System.out.println("Age: ");
                // scanning the interger
                age = Integer.parseInt(scanner.nextLine());
                validAgeInput = true;
                // if user inputs invalid age
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid age.");
            }
        }
        // user friendly message
        System.out.println("Thanks!");
        // storing details
        userData = new Player(0, firstname, lastname, age, 0);
        // storing information
        UserFileHandler userFileHandler = new UserFileHandler();
        userFileHandler.storeUserDataToDatabase(userData);
        return userData;
    }

}
