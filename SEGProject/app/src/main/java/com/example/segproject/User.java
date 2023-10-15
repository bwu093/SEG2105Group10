import java.util.ArrayList;

//this class represents a user
public class User{

    //instance variables
    protected int userID;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected Address address;
    protected boolean registrationStatus;



    //constructor
    public User(int userId, String username, String password, String firstName, String lastName, String phoneNumber, Address address){
        this.userID = userId;
        this.username = username;
        this.password = password;;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationStatus = false;
    }

    public void register(){
        this.registrationStatus = true;
    }

    public boolean login(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
}

