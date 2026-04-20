public class Main {

    public static void main(String[] args) {

        Book hoodFeminism = new Book("Hood Feminism", "Mikki Kendall");
        System.out.println(hoodFeminism.borrowBook());
        System.out.println(hoodFeminism.isBorrowed);
        System.out.println(hoodFeminism.returnBook());
        System.out.println(hoodFeminism.returnBook());
    }
}
