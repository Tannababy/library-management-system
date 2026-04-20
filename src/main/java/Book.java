public class Book {

    private String title;
    private String author;
    boolean isBorrowed;

    // constructor - 2 args
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }


    public String borrowBook(){

        if (isBorrowed()){
            return "This book has been borrowed already.";
        } else {
            setBorrowed(true);
            return "This book is available, you can borrow this book";
        }
    }

    public String returnBook(){
        if (!isBorrowed()){
            return "This book has not been borrowed yet, cannot return!";
        } else {
            setBorrowed(false);
            return "This book has been returned";
        }
    }
}
