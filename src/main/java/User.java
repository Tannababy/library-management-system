import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String name;
    private String email;
    private String password;
    private int id;
    public ArrayList<Book> borrowedBooks = new ArrayList<>();

    static Scanner userScanner = new Scanner(System.in);


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static String returnName() {

        System.out.println("What is your name?");
        return userScanner.nextLine();

    }

    public static String returnEmail() {

        System.out.println("What is your email address?");
        return userScanner.nextLine();
    }

    public static String returnPassword() {
        System.out.println("Please enter a password?");
        return userScanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
