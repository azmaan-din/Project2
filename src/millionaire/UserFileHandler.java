package millionaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azmaan, Yash
 */
// handles the reading and writting of user data
public class UserFileHandler {
    private static final String FILE_PATH = "userData.txt";
    private static final String FILE_PATH_TEMP = "userData.txt.tmp";
    public static void storeUserDataToFile(Data userData) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_TEMP, true))) {

            String line;
            boolean userFound = false;
            while ((line = reader.readLine()) != null) {
                // splits file with commas
                String[] userDataArray = line.split(",");

                /**
                 * if the length is not greater than 2 then its not a valid user data, compares
                 * the users name to the name on file
                 * if the same then updates the line.
                 */

                if (userDataArray.length >= 2 && userDataArray[0].equals(userData.getFirstname())) {
                    line = userData.getFirstname() + "," + userData.getLastname() + "," + userData.getAge() + ","
                            + userData.getMoney();
                    userFound = true;

                }
                //Writies the line 
                writer.write(line);
                writer.newLine();

            }
            // user not found then updates data
            if (!userFound) {
                writer.write(userData.getFirstname() + "," + userData.getLastname() + "," + userData.getAge() + ","
                        + userData.getMoney());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user data to file: " + e.getMessage());
        }
        //Replaces the original file with the temporary file
        replaceFile(FILE_PATH_TEMP, FILE_PATH);

    }
    // retrieving all user data from the file
    public static List<Data> getAllUserData() throws IOException {
        List<Data> userDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                String[] userDataArray = line.split(",");
                if (userDataArray.length >= 4) {
                    userDataList.add(new Data(userDataArray[0], userDataArray[1], Integer.parseInt(userDataArray[2]),
                            Integer.parseInt(userDataArray[3])));
                }
            }
        }
        return userDataList;
    }
    //replace the original file with a temporary file
    private static void replaceFile(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);

        if (!sourceFile.exists()) {
            System.err.println("Source file does not exist: " + sourcePath);
            return;
        }

        if (destinationFile.exists()) {
            if (!destinationFile.delete()) {
                System.err.println("Failed to delete destination file: " + destinationPath);
                return;
            }
        }
        // renaming the temporary file to the original file
        try {
            if (!sourceFile.renameTo(destinationFile)) {
                System.err.println("Failed to rename source file to destination file.");
            }
        } catch (Exception e) {
            System.err.println("Error replacing file: " + e.getMessage());
        }
    }

}
