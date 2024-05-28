// @author Azmaan, Yash
// This class represents the rules of the game.
package millionaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Rules {
    //storing the game rules
    private String game;
    
    public Rules() {
        this.game = "";
    }
    //reading the game rules from a file
    public String getRules() throws FileNotFoundException, IOException {
        // Path way to the game rules file 
        String gameRulesFilePath = "rules.txt";
        
        try (FileReader file = new FileReader(gameRulesFilePath);
             BufferedReader input = new BufferedReader(file)) {
            
            String line = input.readLine();
            // reads file and adds to the game rules string
            while(line != null){
                game += "\n" + line;               
                line = input.readLine();
            }
        }   
       
        return game;
    }
    // get the game rules
    public String getGameRules() {
        return game;
    }
    public void init() throws FileNotFoundException, IOException {
        this.game = getRules();
    }
}
