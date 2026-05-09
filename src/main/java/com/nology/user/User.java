package com.nology.user;

import com.nology.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

    private String name;
    private String email;
    private String password;
    private boolean isAdmin = false;

    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    static Scanner userScanner = new Scanner(System.in);


    public User(String name, String email, String password, boolean isAdmin) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String name, String email, String password, boolean isAdmin, ArrayList<Book> borrowedBooks) {

        this(name,email, password, isAdmin);
        this.borrowedBooks = borrowedBooks;
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

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getBorrowedBooksIds() {

        if (borrowedBooks.isEmpty()) {
            return "";
        }

        int[] listOfBookIds = new int[borrowedBooks.size()];

        for (int j = 0; j < borrowedBooks.size(); j++) {

            int bookRef = borrowedBooks.get(j).getId();
            listOfBookIds[j] = bookRef;
        }



        return Arrays.toString(listOfBookIds);
    }


//    public String listOfBorrowedBooks() {
//
//        StringBuilder listOfBooks = new StringBuilder();
//        if (borrowedBooks.isEmpty()) {
//
//            return "";
//
//        } else {
//            for (Book book : borrowedBooks) {
//
//                listOfBooks.append(book.getTitle());
//                listOfBooks.append("|");
//                listOfBooks.append(book.getAuthor());
//                if (book != borrowedBooks.getLast()){
//                    listOfBooks.append(";");
//                }
//            }
//        }
//        return listOfBooks.toString();
//    }


    @Override
    public String toString() {
        return "User: " +  this.getName() + ", Email: " + this.getEmail();
    }

}
