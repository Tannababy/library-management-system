package com.nology;

import com.nology.user.Admin;
import com.nology.user.User;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();


    public boolean isUser(User user) {

        for (int i = 0; i < users.size(); i++) {

            User foundUser = users.get(i);

            if (user.getName().equalsIgnoreCase(foundUser.getName()) && user.getEmail().equalsIgnoreCase(foundUser.getEmail())) {

                System.out.println("Welcome back " + foundUser.getName());
                return true;
            }
        }
        return false;
    }

    public void addUserToUsersList(User user) {

        if (!(isUser(user))) {

            users.add(user);
            System.out.println("User account for " + user.getName() + " has been created successfully");
        } else {
            System.out.println("User account already exists!");
        }
    }



    public void addBook(User user, Book book) {

        if (!(user instanceof Admin)) {

            System.out.println("You are not authorised to add books to library");

        } else {
            books.add(book);
        }
    }

    public void deleteBook(User user, Book book) {

        if (!(user instanceof Admin)) {

            System.out.println("You are not authorised to remove books to library");

        } else {
            books.remove(book);
        }
    }

    public void borrowBook(User user, String bookTitle) {

        // loops through list of library books
        for (int i = 0; i < books.size(); i++) {

            Book libraryBook = books.get(i);

            // checks if given bookTitle matches a book in the list of books
            if (bookTitle.trim().equalsIgnoreCase(libraryBook.getTitle())) {

                //checks if library book has already been borrowed
                if (libraryBook.isBorrowed()) {
                    System.out.println("Book already borrowed");
                    return;
                }


                libraryBook.setBorrowed(true);
                libraryBook.setBorrowedBy(user);
                user.borrowedBooks.add(libraryBook);

                System.out.println("Book: " + libraryBook.getTitle() + ", borrowed by user: " + user.getName());
                return;

            }

        }
        System.out.println("Book: " + bookTitle + ", was not found in library");
    }

    public void returnBook(User user, String bookTitle) {

        // loops through users borrowed books list
        for (int i = 0; i < user.borrowedBooks.size(); i++) {

            Book returnedBook = user.borrowedBooks.get(i);

            // checks if given bookTitle matches a book in the list of users borrowed books
            if (bookTitle.trim().equalsIgnoreCase(returnedBook.getTitle())) {

                returnedBook.setBorrowed(false);
                returnedBook.setBorrowedBy(null);
                user.borrowedBooks.remove(returnedBook);

                System.out.println("Book: " + bookTitle + ", has been returned by user: " + user.getName());
                return;
            }

        }

        System.out.println("Book: " + bookTitle + ", was not borrowed by user: " + user.getName() + ". It can't be returned.");

    }


    public void displayBooks(){
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }



}
