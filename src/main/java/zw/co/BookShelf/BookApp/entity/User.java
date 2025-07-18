package zw.co.BookShelf.BookApp.entity;
import  jakarta.persistence.*;

/**
 * Represents a user entity in the BookApp application.
 * This class is used to define the properties and behavior of a user.
 */



@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    @Column(unique=true)
    private String email;

    private String password;

    private boolean role;


    public User(){}
    public User(long id, String firstName, String lastName, String userName, String email, String password, boolean role){

        this.id  = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;


    }

    public long getId (){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getFirstName (){

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
