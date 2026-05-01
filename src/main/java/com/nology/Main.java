package com.nology;

import com.nology.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);


        Library myLibrary = new Library();

        User currentUser;

        int startOption = startMenu(myScanner);

        // Create account
        if (startOption == 1) {

            String inputName = User.returnName();
            String inputEmail = User.returnEmail();
            String inputPassword = User.returnPassword();

            if (myLibrary.emailExists(inputEmail)) {

                System.out.println("This email has already been used, please use another one to create account");


            } else {

                User newUser = new User(inputName, inputEmail, inputPassword);
                myLibrary.addUserToUsersList(newUser);
                currentUser = newUser;


                handleUserOptions(currentUser, displayUserMenu(myScanner), myLibrary, myScanner);
            }

            // Login
        } else if (startOption == 2) {

            myScanner.nextLine();
            currentUser = myLibrary.findUser(User.returnEmail(), User.returnPassword());

            if (currentUser == null) {

                System.out.println("Account not found, please check login details and re-attempt login or create an account.");
            } else {

                System.out.println("Welcome back " + currentUser.getName());


                handleUserOptions(currentUser, displayUserMenu(myScanner), myLibrary, myScanner);

            }

        } else {
            System.out.println("Incorrect selection, please choose 1 for login or 2 to create an account!");
        }









    }

    public static int startMenu(Scanner scanner) {


        System.out.println("Welcome to Sunshine Library :D");
        System.out.println("Choose between:\n1: Create Account\n2: Login");

        return scanner.nextInt();

    }


    public static int displayUserMenu(Scanner scanner) {


        String menu = "MENU:\n1 - Display all books\n2 - Borrow book\n3 - Return book";

        System.out.println("-------------------------------");
        System.out.println(menu);

        return scanner.nextInt();
    }

    public static void handleUserOptions(User currentUser, int userMenuOption, Library library, Scanner scanner) {


        switch (userMenuOption) {

            case 1 -> library.displayBooks();
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