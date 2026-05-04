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

            writer.write("Username," + "Email," + "Password"); //rewrites header
            writer.newLine();

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
            users.clear();

            while ((line = reader.readLine()) != null) { // continuously reads a line of text from opened file

                if (line.contains("Username")){ // skip header
                    continue;
                }

                String[] values = line.split(",");
                List<String> row = Arrays.asList(values);

                String userName, userEmail, userPassword;

                    userName = row.get(0);
                    userEmail = row.get(1);
                    userPassword = row.get(2);

                    User newUser = new User(userName, userEmail, userPassword);
                    users.add(newUser);


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


    public void loadBooks(String filePath) {


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) { // reads each line in csv till there's no more lines

                ArrayList<String> rowOfFields = new ArrayList<>();
                StringBuilder currentField = new StringBuilder();
                boolean insideQuote = false; // keeps track of leading/trailing quotation marks

                if (line.contains("SubGenre") && line.contains("Publisher")) { // skip header
                    continue;
                }

                for (int i = 0; i < line.length(); i++) {

                    char character = line.charAt(i);
                    if (character == '"') {

                        insideQuote = !insideQuote;

                    } else if (character == ',' && !insideQuote) { // checks for commas that aren't inside quotation marks

                        rowOfFields.add(currentField.toString()); // adds the current row field built to a list
                        currentField.delete(0, currentField.length()); // resets the currentField variable

                    } else {
                        currentField.append(character); // builds the field char by char
                    }
                }

                rowOfFields.add(currentField.toString());

                Book storedBook = new Book(rowOfFields.get(1), rowOfFields.get(2), false, null);
                books.add(storedBook);

            }



        } catch (IOException e) {
            System.out.println("Error reading file.");
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
                libraryBook.setBorrowedByEmail(user.getEmail());
                user.borrowedBooks.add(libraryBook);
                libraryBook.increaseBorrowCount();

                System.out.println("Book: " + libraryBook.getTitle() + ", borrowed by user: " + libraryBook.getBorrowedByEmail());
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
                returnedBook.setBorrowedByEmail(null);
                user.borrowedBooks.remove(returnedBook);

                System.out.println("Book: " + bookTitle + ", has been returned by user: " + user.getName());
                return;
            }

        }

        System.out.println("Book: " + bookTitle + ", was not borrowed by user: " + user.getName() + ". It can't be returned.");

    }


    public  void displayAvailableBooks(){

        int newLength = 0;
        if (books.isEmpty()){

            System.out.println("There are no books currently available in the library");
        } else {
            for (int i = 0; i < books.size(); i++) {

                Book borrowedBook = books.get(i);

                if(borrowedBook.isBorrowed()) {
                } else {
                    System.out.println(books.get(i));
                    newLength++;
                    System.out.println("=======================================");
                }

            }

            System.out.println("Number of Available books: " + newLength);
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
