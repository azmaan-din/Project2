// @author Azmaan, Yash
package millionaire;

public class Data {
    //storing userid,firstname,lastname,age,money
    private int userid;
    private String firstname;
    private String lastname;
    private int age;
    private int money;
    
    //constructor
    public Data(int userid, String firstname, String lastname, int age, int money) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.money = money;
    }
    //getter for id
    public int getUserId(){
        return userid;
    }
    //setter for id
    public void setUserId(int userid){
        this.userid = userid;
    }
    //getter for firstname
    public String getFirstname() {
        return firstname;
    }
    //setter for firstname
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    //getter for lastname
    public String getLastname() {
        return lastname;
    }
    //setter for lastname
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    //getter for age
    public int getAge() {
        return age;
    }
    //setter for age
    public void setAge(int age) {
        this.age = age;
    }
    //getter for money
    public int getMoney() {
        return money;
    }
    //setter for money
    public void setMoney(int money) {
        this.money = money;
    }
    //update monkey
    public void updateMoney(int money){
        this.money = this.money+money;
    }


}
