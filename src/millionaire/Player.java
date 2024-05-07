package millionaire;

/**
 *
 * @author Azmaan, Yash
 */
public class Player extends Data {
    public Player(String firstname, String lastname, int age, int money) {
        // calling the constructor 
        super(firstname, lastname, age, money);

    }

    // friendly message for player
    public String Intro() {
        return "Hello " + getFirstname() + " " + getLastname()
                + " welcome to who wants to be a millionaire you are competeing for 1,000,000\n";
    }

    // message when palyer wins money
    public String ending() {
        return "Thanks for playing today " + getFirstname() + " you have won " + getMoney();
    }
    // message when player loses
    public String gameOver() {
        return "Errrrr you have lost\n";
    }

    // End message when the user wins the game show
    public String millionare(){
        return "Congratulations You have won Who Want to be a Millionaire\n";
    }
}
