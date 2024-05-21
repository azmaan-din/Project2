package millionaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Rules {
    private String game;

    public Rules() {
        this.game = "";
    }

    // Reading the game rules from a file
    public String getRules() throws FileNotFoundException, IOException {
        String gameRulesFilePath = "rules.txt";
        
        try (FileReader file = new FileReader(gameRulesFilePath);
             BufferedReader input = new BufferedReader(file)) {
            
            StringBuilder stringBuilder = new StringBuilder();
            String line = input.readLine();
            
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = input.readLine();
            }
            
            game = stringBuilder.toString();
        }
        
        return game;
    }

    // Initialize the game rules
    public void init() throws FileNotFoundException, IOException {
        this.game = getRules();
    }

    // Get the game rules
    public String getGameRules() {
        return game;
    }
}
