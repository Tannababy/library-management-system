package com.nology;

import com.nology.user.Admin;
import com.nology.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();


    public void saveUsers(String filePath) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String name, email, password;
            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                name = user.getName();
                email = user.getEmail();
                password = user.getPassword();

                String row = name + "," + email + "," + password;
                writer.write(row);
                writer.newLine();


            }

            System.out.println("Successfully saved users.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    public void loadUsers(String filePath) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.contains("Username")){
                    continue;
                }

                String[] values = line.split(",");
                List<String> row = Arrays.asList(values);

                String userName, userEmail, userPassword;
                for (int i = 0; i < row.size(); i++) {

                    userName = row.get(0);
                    userEmail = row.get(1);
                    userPassword = row.get(2);

                    User newUser = new User(userName, userEmail, userPassword);
                    users.add(newUser);

                }

            }

        } catch (IOException e) {

            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public boolean emailExists(String email) {

        for (int i = 0; i < users.size(); i++) {

            if (email.trim().equalsIgnoreCase(users.get(i).getEmail())){

                return true;
            }

        }
        return false;

    }

    public User findUser(String email, String password) {

        for (int i = 0; i < users.size(); i++) {

            User foundUser = users.get(i);

            if (email.trim().equalsIgnoreCase(foundUser.getEmail())) {

                if (password.trim().equalsIgnoreCase(foundUser.getPassword())) {

                    return foundUser;
                }
            }
        }
        return null;
    }

    public void addUserToUsersList(User user) {

        if (findUser(user.getEmail(), user.getPassword()) == null) {

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


    public  void displayBooks(){
        if (books.isEmpty()){
            System.out.println("There are no books currently available in the library");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }


    public void displayUsers(){

        if (users.isEmpty()){
            System.out.println("There are no books currently available in the library");
        } else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i));
            }
        }

    }

}
