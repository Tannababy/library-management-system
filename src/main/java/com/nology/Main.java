package com.nology;

import com.nology.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Library myLibrary = new Library();

        Book hoodFeminism = new Book("Hood Feminism", "Mikki Kendall");
        Book pet = new Book("Pet", "Lotanna Amobi");


        System.out.println("Welcome to Sunshine Library :D");


        User newUser = new User(User.returnName(), User.returnEmail(), User.returnPassword());



        displayMenu();
        int userOption = scanner.nextInt();
        scanner.nextLine(); // to consume extra line

        switch (userOption) {
            case 1 -> {
                System.out.println("Add book to library");

                System.out.println("Enter book name");
                String bookTitle = scanner.nextLine();
//                myLibrary.addBook(newUser, bookTitle);
            }
            case 2 -> {

                System.out.println("Enter book name");
                String bookTitle = scanner.nextLine();
                myLibrary.borrowBook(newUser, bookTitle);
            }
            case 3 -> {

                System.out.println("Enter book name");
                String bookTitle = scanner.nextLine();
                myLibrary.returnBook(newUser, bookTitle);
            }


            case 4 -> System.out.println("Display all books");
        }

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



}
