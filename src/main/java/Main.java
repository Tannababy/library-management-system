import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayMenu();
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1 -> System.out.println("Create a book");
            case 2 -> System.out.println("Add book to library");
            case 3 -> System.out.println("Borrow book");
            case 4 -> System.out.println("Return book");
            case 5 -> System.out.println("Display all books");
        }

        Book hoodFeminism = new Book("Hood Feminism", "Mikki Kendall");
        Book pet = new Book("Pet", "Lotanna Amobi");

        System.out.println(hoodFeminism);

        Library myLibrary = new Library();
        myLibrary.addBook(hoodFeminism);
        myLibrary.addBook(pet);

        myLibrary.displayBooks();





        scanner.close();

    }

    public static void displayMenu() {

        String menu = "MENU:\n1 - Create a book\n2 - Add book to library\n3 - Borrow book\n4 - Return book\n5 - Display all books";

        System.out.println("Welcome to Sunshine Library :D");
        System.out.println("-------------------------------");
        System.out.println(menu);

    }


}
