package millionaire;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.BorderLayout;

public class RulesDisplay extends JPanel {

    private JTextArea rulesTextArea;

    public RulesDisplay() {
        setLayout(new BorderLayout());

        rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        add(rulesTextArea, BorderLayout.CENTER);

        loadRules();
    }

    private void loadRules() {
        StringBuilder rules = new StringBuilder();
        String gameRulesFilePath = "rules.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(gameRulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rules.append(line).append("\n");
            }
        } catch (IOException e) {
            rules.append("Rules could not be loaded.");
        }

        rulesTextArea.setText(rules.toString());
    }
}
