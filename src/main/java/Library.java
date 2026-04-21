import java.util.ArrayList;

public class Library {

    private ArrayList<Book> bookList = new ArrayList<>();


    public boolean checkForBook(ArrayList<Book> bookList, Book book){

        for (int i = 0; i < bookList.size(); i++) {

            if (bookList.contains(book)) {
                return true;
            }
        }
            return false;
    }

    public void addBook(Book newBook){

       if (checkForBook(bookList, newBook)){
           System.out.println("This book already exists in the library. It cannot be added again");
       }
        else {
            bookList.add(newBook);
           System.out.println("This book has been added to library");
        }
    }

    public void displayBooks(){
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i));
        }
    }


}
