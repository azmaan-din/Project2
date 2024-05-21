package millionaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azmaan, Yash
 */
public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    // method starting game
    public static void Game() throws IOException, SQLException {
        // getting user information
        Player userData = CollectUserData.collectUserData(scanner);
        UserFileHandler userFileHandler = new UserFileHandler();
        // printing player details
        System.out.println(userData.Intro());
        LinkedList<Questions> allQuestions = null;
        try {
            allQuestions = Questions.createQuestionBank("question_bank.txt");
        } catch (IOException e) {
            System.err.println("Error reading question bank file: " + e.getMessage());
            return;
        }
        // using bufferedreader to read information efficiently 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int prizeMoney = 2500;
        int questCount = 0;
        int round = 1;
        try {
            for (Questions question : allQuestions) {
                // printing the questions
                System.out.println("Question: " + question.getQuestion());
                // printing the options
                System.out.println("Options:");
                LinkedList<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    System.out.println(options.get(i));
                }

                prizeMoney = prizeMoney * 2;

                String userAnswer = reader.readLine(); 

                // if user press 'x' exits the game
                if ("x".equalsIgnoreCase(userAnswer)) {
                    userFileHandler.storeUserDataToDatabase(userData);
                    System.out.println("Exiting game... Thank you for playing.");
                    System.exit(0);

                }
                if (userAnswer.isEmpty()) { 
                    continue; 
                }

                if (questCount == 1) {
                    round++;
                    if (round != 5) {
                        System.out.println("You have completed round " + (round - 1)
                                + "\n Enter yes if you wish to continue or press enter to take your winnings");
                        // Reading user input
                        String proceedAnswer = reader.readLine().trim();
                        if (proceedAnswer.equalsIgnoreCase("yes")) {
                            System.out.println("testing sucsess");
                           
                            questCount = 0;
                            continue;
                        } else {
                            // breaks through the code
                            break;
                        }
                    } else {
                        System.out.println("You finished");
                    }

                }
                // checking answer
                if (question.getAnswer().equalsIgnoreCase(userAnswer)) {
                    System.out.println("Correct answer!");
                    // updating money amount
                    userData.updateMoney(prizeMoney);
                    System.out.println(userData.getMoney());
                    questCount++;
                } else {
                    System.out.println("Incorrect answer!");
                    userData.setMoney(0);
                    System.out.println(userData.gameOver());
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // storing user data in the database
                userFileHandler.storeUserDataToDatabase(userData);
            } catch (SQLException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "An error occurred while storing user data.", ex);
            }
            // showing end message
            System.out.println(userData.ending());
            // staring game
            Start2.Start();

        }
    }
}
