public class Main {

    public static void main(String[] args) {

        Book hoodFeminism = new Book("Hood Feminism", "Mikki Kendall");
        Book pet = new Book("Pet", "Lotanna Amobi");
//        System.out.println(hoodFeminism.borrowBook());
//        System.out.println(hoodFeminism.isBorrowed);
//        System.out.println(hoodFeminism.returnBook());
//        System.out.println(hoodFeminism.returnBook());

        System.out.println(hoodFeminism);

        Library myLibrary = new Library();
        myLibrary.addBook(hoodFeminism);
        myLibrary.addBook(pet);

        myLibrary.displayBooks();
    }
}
