// @author Azmaan, Yash
package millionaire;

public class Data {
    //storing the first name
    private String firstname;
    //storing the last name
    private String lastname;
    //storing the age 
    private int age;
    //storing money
    private int money;

    public Data(String firstname, String lastname, int age, int money) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.money = money;
    }

    // get the first name
    public String getFirstname() {
        return firstname;
    }
    // sets the firstname
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    // sets the lastname
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // gets the age
    public int getAge() {
        return age;
    }
    // sets the age
    public void setAge(int age) {
        this.age = age;
    }

    // gets the money
    public int getMoney() {
        return money;
    }
    // sets the money
    public void setMoney(int money) {
        this.money = money;
    }
    // updates the tally
    public void updateMoney(int money){
        this.money = this.money+money;
    }


}
