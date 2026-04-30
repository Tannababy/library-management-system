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

        User newUser;
        User currentUser;

        if (startOption == 1) {

         String newUserName = User.returnName();
         String newUserEmail = User.returnEmail();
         String newUserPassword = User.returnPassword();

         newUser = new User(newUserName, newUserEmail, newUserPassword);

         if (!(myLibrary.isUser(newUser))) {

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


            System.out.println("Coming soon");

        } else {
            System.out.println("Incorrect selection, please choose 1 for login or 2 to create an account!");
        }



//        Book hoodFeminism = new Book("Hood Feminism", "Mikki Kendall");
//        Book pet = new Book("Pet", "Lotanna Amobi");



//        System.out.println(hoodFeminism);

//        myLibrary.addBook(hoodFeminism);
//        myLibrary.addBook(pet);


//        myLibrary.displayBooks();




        scanner.close();

    }

    public static void displayMenu() {

        String menu = "MENU:\n1 - Add book to library\n2 - Borrow book\n3 - Return book\n4 - Display all books";

        System.out.println("-------------------------------");
        System.out.println(menu);

    }

    public static void displayUserMenu() {

        String menu = "MENU:\n1 - Display all books\n2 - Borrow book\n3 - Return book";

        System.out.println("-------------------------------");
        System.out.println(menu);

    }

}
