import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        books.add(book);
    }

    public void borrowBook(User user, String bookTitle) {

        boolean isFound = false;
        //
        for (int i = 0; i < books.size(); i++) {

            Book libraryBook = books.get(i);

            // checks if given bookTitle matches a book in the list of books
            if (bookTitle.trim().equalsIgnoreCase(libraryBook.getTitle())) {

                isFound = true;

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


    public void displayBooks(){
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }


}
