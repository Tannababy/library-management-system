package com.nology;

import com.nology.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);


        Library myLibrary = new Library();

        User currentUser = null;

        while (currentUser == null) {

            startMenu();
            int startOption = myScanner.nextInt();
            myScanner.nextLine();

            // Create account
            if (startOption == 1) {

                String inputName = User.returnName();
                String inputEmail = User.returnEmail();
                String inputPassword = User.returnPassword();

                // check for unique email
                if (myLibrary.emailExists(inputEmail)) {

                    System.out.println("This email has already been used, please use another one to create account");


                } else {

                    // account creation
                    User newUser = new User(inputName, inputEmail, inputPassword);
                    myLibrary.addUserToUsersList(newUser);
                    currentUser = newUser;

                }

                // Login
            } else if (startOption == 2) {

                // update currentUser state with saved user
                currentUser = myLibrary.findUser(User.returnEmail(), User.returnPassword());

                if (currentUser == null) {

                    System.out.println("Account not found, please check login details and re-attempt login or create an account.");
                } else {

                    System.out.println("Welcome back " + currentUser.getName());

                }

            } else {
                System.out.println("Incorrect selection, please choose 1 for login or 2 to create an account!");
            }
        }

        boolean isRunning = true;

        while (isRunning) {


            displayUserMenu();
            int userOption = myScanner.nextInt();
            myScanner.nextLine();

            if (userOption == 0){

                isRunning = false;
                System.out.println("Existing the application, bye!");
            }
            else {
                handleUserOptions(currentUser, userOption, myLibrary, myScanner);
            }


        }








    }

    public static void startMenu() {


        System.out.println("Welcome to Sunshine Library :D");
        System.out.println("Choose between:\n1: Create Account\n2: Login");


    }


    public static void displayUserMenu() {


        String menu = "MENU:\n1 - Display all books\n2 - Borrow book\n3 - Return book\n0 - Exit";

        System.out.println("-------------------------------");
        System.out.println(menu);

    }

    public static void handleUserOptions(User currentUser, int userMenuOption, Library library, Scanner scanner) {


        switch (userMenuOption) {

            case 1 -> {
                library.displayBooks();
            }
            case 2 -> {
                System.out.println("Enter book name");
                String title = scanner.nextLine();
                library.borrowBook(currentUser, title);

            }
            case 3 -> {
                System.out.println("Enter book name");
                String title = scanner.nextLine();
                library.returnBook(currentUser, title);

            }

        }


    }

}