package com.nology;

import com.nology.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Library myLibrary = new Library();


        System.out.println("Welcome to Sunshine Library :D");
        System.out.println("Choose between:\n1: Create Account\n2: Login");
        int startOption = scanner.nextInt();
        scanner.nextLine();


        User currentUser;

        if (startOption == 1) {

            User newUser = new User(User.returnName(), User.returnEmail(), User.returnPassword());

            if (myLibrary.emailExists(newUser.getEmail())) {

                System.out.println("This email has already been used, please use another one to create account");


            } else {

                myLibrary.addUserToUsersList(newUser);
                currentUser = newUser;

                displayUserMenu();
                int userMenuOption = scanner.nextInt();
                scanner.nextLine();

                switch (userMenuOption) {

                    case 1 -> myLibrary.displayBooks();
                    case 2 -> {
                        System.out.println("Enter book name");
                        String title = scanner.nextLine();
                        myLibrary.borrowBook(currentUser, title);
                    }
                    case 3 -> {
                        System.out.println("Enter book name");
                        String title = scanner.nextLine();
                        myLibrary.returnBook(currentUser, title);
                    }
                }
            }

        } else if (startOption == 2) {

            currentUser = myLibrary.findUser(User.returnEmail(), User.returnPassword());

            if (currentUser == null) {

                System.out.println("Account not found, please check login details and re-attempt login or create an account.");
            } else {

                System.out.println("Welcome back " + currentUser.getName());

                displayUserMenu();
                int userMenuOption = scanner.nextInt();
                scanner.nextLine();

                switch (userMenuOption) {

                    case 1 -> myLibrary.displayBooks();
                    case 2 -> {
                        System.out.println("Enter book name");
                        String title = scanner.nextLine();
                        myLibrary.borrowBook(currentUser, title);
                    }
                    case 3 -> {
                        System.out.println("Enter book name");
                        String title = scanner.nextLine();
                        myLibrary.returnBook(currentUser, title);
                    }
                }
            }

        } else {
            System.out.println("Incorrect selection, please choose 1 for login or 2 to create an account!");
        }







        scanner.close();

    }


    public static void displayUserMenu() {

        String menu = "MENU:\n1 - Display all books\n2 - Borrow book\n3 - Return book";

        System.out.println("-------------------------------");
        System.out.println(menu);

    }

//    public static void handleUserOptions(User currentUser) {
//
//        Scanner myScanner = new Scanner(System.in);
//
//        int userMenuOption = myScanner.nextInt();
//        myScanner.nextLine();
//
//        switch (userMenuOption) {
//
//            case 1 -> myLibrary.displayBooks();
//            case 2 -> {
//                System.out.println("Enter book name");
//                String title = myScanner.nextLine();
//                myLibrary.borrowBook(currentUser, title);
//            }
//            case 3 -> {
//                System.out.println("Enter book name");
//                String title = myScanner.nextLine();
//                myLibrary.returnBook(currentUser, title);
//            }
//        }
//    }

}
