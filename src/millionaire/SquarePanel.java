package millionaire;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

//this panel is used to show the user progress in the game
public class SquarePanel extends JPanel {
    //max sqaure
    private static final int MAX_SQUARES = 8;
    //holds array
    private JPanel[] squares;

    //constructor for settin the square panels
    public SquarePanel() {
        setLayout(new GridLayout(1, MAX_SQUARES));
        squares = new JPanel[MAX_SQUARES];
        initializeSquares();
    }
    
    private void initializeSquares() {
        for (int i = 0; i < MAX_SQUARES; i++) {
            //setting the size and colour of the panels and boarders
            squares[i] = new JPanel();
            squares[i].setPreferredSize(new Dimension(30, 30));
            squares[i].setBackground(Color.WHITE);
            squares[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(squares[i]);
        }
    }
    //colour to fill out the panel with if person gets question right or wrong
    public void fillSquare(int index, boolean correct) {
        if (index >= 0 && index < MAX_SQUARES) {
            squares[index].setBackground(correct ? Color.GREEN : Color.RED);
        }
    }
}
