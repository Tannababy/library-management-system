package com.nology;

import com.nology.user.Admin;
import com.nology.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();


    public void loadBooksFromJson(String filePath) {

        books.clear();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            StringBuilder fullBookJsonArr = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fullBookJsonArr.append(line);
            }

            JSONArray listOfBooksJson = new JSONArray(fullBookJsonArr.toString());

            for (int i = 0; i < listOfBooksJson.length(); i++) {

                JSONObject bookObj = listOfBooksJson.getJSONObject(i);

                int bookId = bookObj.getInt("id");
                String title = bookObj.getString("title");
                String author = bookObj.getString("author");
                boolean isBorrowed = bookObj.getBoolean("isBorrowed");
                String borrowedBy = bookObj.getString("borrowedBy") == null ? null : bookObj.getString("borrowedBy");
                int borrowCount = bookObj.getInt("borrowCount");

                Book libraryBook = new Book(bookId, title, author, isBorrowed, borrowedBy, borrowCount);
                books.add(libraryBook);
            }


        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

    }



    public JSONArray buildBooksJson() {

        JSONArray booksArrJson = new JSONArray();

        for (int i = 0; i < books.size(); i++) {

            Book librarybook = books.get(i);

            JSONObject jsonBook = new JSONObject();

            jsonBook.put("id", librarybook.getId());
            jsonBook.put("title", librarybook.getTitle());
            jsonBook.put("author", librarybook.getAuthor());
            jsonBook.put("isBorrowed", librarybook.isBorrowed());
            jsonBook.put("borrowedBy", librarybook.getBorrowedByEmail());
            jsonBook.put("borrowCount", librarybook.getBorrowCount());

            booksArrJson.put(jsonBook);
        }

        return booksArrJson;

    }


    public void writeToJsonFile(String filepath) {

        JSONArray jsonArray = buildBooksJson();


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))){

            bufferedWriter.write(jsonArray.toString(2));

        } catch (IOException e) {

            System.err.println("JSON file not found: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void saveUsers(String filePath) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("Username," + "Email," + "Password," + "BooksBorrowedById," + "IsAdmin"); //rewrites header
            writer.newLine();

            String name, email, password, booksBorrowedById = "";
            boolean isAdmin;
            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                name = user.getName();
                email = user.getEmail();
                password = user.getPassword();
                isAdmin = user.isAdmin();

                if (!isAdmin) {

                    booksBorrowedById = user.getBorrowedBooksIds();
                    String row = name + "," + email + "," + password + "," + booksBorrowedById + "," + isAdmin;
                    writer.write(row);
                    writer.newLine();
                } else {

                    String row = name + "," + email + "," + password + "," + "[ ]" + "," + isAdmin;
                    writer.write(row);
                    writer.newLine();
                }



            }


        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }

    }


    public void loadUsers(String filePath) {

        users.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) { // continuously reads a line of text from opened file

                if (line.contains("Username") && line.contains("Password")) { // skip header
                    continue;
                }

                String[] values = line.split(",");
                List<String> row = Arrays.asList(values);

                String userName, userEmail, userPassword, userBorrowedBooks;

                userName = row.get(0);
                userEmail = row.get(1);
                userPassword = row.get(2);
                userBorrowedBooks = row.get(3);
                // [ 1, 2, 3, 5, 23 ]
                ArrayList<Book> usersBorrowedBooksList = new ArrayList<>();

                if (!userBorrowedBooks.trim().equals("[]") && !userBorrowedBooks.trim().isEmpty())  {

                    usersBorrowedBooksList = new ArrayList<>();

                    userBorrowedBooks = userBorrowedBooks.replace("[","").replace("]","");

                    String[] listOfIds = userBorrowedBooks.split(",");

                    for (int i = 0; i < listOfIds.length; i++) {

                        String idString = listOfIds[i].trim();

                        if (!idString.isEmpty()) {

                            int id =  Integer.parseInt(idString);

                            for (int j = 0; j < books.size(); j++) {

                                if (books.get(j).getId() == id) {

                                    usersBorrowedBooksList.add(books.get(j));
                                }
                            }
                        }

                    }

                }


                User newUser = new User(userName, userEmail, userPassword, Boolean.parseBoolean(row.getLast()), usersBorrowedBooksList);
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





//    public void addBook(User user, Book book) {
//
//        if (!(user instanceof Admin)) {
//
//            System.out.println("You are not authorised to add books to library");
//
//        } else {
//            books.add(book);
//        }
//    }
//
//    public void deleteBook(User user, Book book) {
//
//        if (!(user instanceof Admin)) {
//
//            System.out.println("You are not authorised to remove books to library");
//
//        } else {
//            books.remove(book);
//        }
//    }

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
                user.getBorrowedBooks().add(libraryBook);
                libraryBook.increaseBorrowCount();
                saveUsers("src/main/java/com/nology/user/users.csv");
                writeToJsonFile("src/main/java/com/nology/books.json");

                System.out.println("Book: " + libraryBook.getTitle() + ", borrowed by user: " + libraryBook.getBorrowedByEmail());
                return;

            }

        }
        System.out.println("Book: " + bookTitle + ", was not found in library");
    }

    public void returnBook(User user, String bookTitle) {

        // loops through users borrowed books list
        for (int i = 0; i < user.getBorrowedBooks().size(); i++) {

            Book returnedBook = user.getBorrowedBooks().get(i);

            // checks if given bookTitle matches a book in the list of users borrowed books
            if (bookTitle.trim().equalsIgnoreCase(returnedBook.getTitle())) {

                returnedBook.setBorrowed(false);
                returnedBook.setBorrowedByEmail(null);
                user.getBorrowedBooks().remove(returnedBook);
                saveUsers("src/main/java/com/nology/user/users.csv");
                writeToJsonFile("src/main/java/com/nology/books.json");

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


//    public void displayUsers(){
//
//        if (users.isEmpty()){
//            System.out.println("There are no books currently available in the library");
//        } else {
//            for (int i = 0; i < users.size(); i++) {
//                System.out.println(users.get(i));
//            }
//        }
//
//    }

    public void displayAllBooks() {

        if (books.isEmpty()){

            System.out.println("There are no books currently available in the library");
        } else {
            for (int i = 0; i < books.size(); i++) {

                System.out.println(books.get(i));
                System.out.println("=======================================");
                }

            }

    }

    public void displayCurrentlyBorrowedBooks() {

        if (books.isEmpty()){

            System.out.println("There are no books currently available in the library");
        } else {

            System.out.println("All the books listed below are currently out on loan.");
            for (int i = 0; i < books.size(); i++) {

                Book book = books.get(i);

                if (book.isBorrowed()) {

                    System.out.println("==========================================");
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Times Borrowed: " + book.getBorrowCount());
                    System.out.println("Status: Borrowed");
                    System.out.println("==========================================");
                }
            }

        }

    }

    public void displayAllBooksBorrowCount() {

        if (books.isEmpty()) {
            System.out.println("There are no books currently available in the library");
        } else {
            System.out.println("Below is a complete list of books with their borrow count and loan status.");
            for (int j = 0; j < books.size(); j++) {

                String status = books.get(j).isBorrowed() ? "Borrowed" : "Available";

                System.out.println("Title: " + books.get(j).getTitle());
                System.out.println("Author: " + books.get(j).getAuthor());
                System.out.println("Times Borrowed: " + books.get(j).getBorrowCount());
                System.out.println("Status: " + status);
                System.out.println("==========================================");
            }
        }

    }

    public void createReport(User user) {

        if (!(user.isAdmin())) {
            System.out.println("Unauthorised access! Only Admin's can create reports!");
        } else {

            System.out.println("Report on the current state of the books in the library");
            System.out.println("==========================================");
            displayCurrentlyBorrowedBooks();
            displayAllBooksBorrowCount();
        }
    }


}
